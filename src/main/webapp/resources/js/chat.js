/**
 * 
 */
var stompClient = null;
var username = null;

function connect(event) {
    username = document.querySelector('#name').value.trim();

    if (username) {
        var socket = new SockJS('/ws-chat');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}

function onConnected() {
    stompClient.subscribe('/topic/public'+roomId, onMessageReceived);

    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({messageType: 'ENTER', empName: username})
    )
}

function onError(error) {
    console.log('Could not connect to WebSocket server. Please refresh this page to try again!');
}

function sendMessage(event) {
    var messageContent = document.querySelector('#chatMessage').value.trim();
    
    if (messageContent && stompClient) {
        var chatMessage = {
            messageType: 'TALK',
            empName: username,
            chatContent: messageContent,
            roomId: roomId
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        document.querySelector('#chatMessage').value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    var messageElement = document.createElement('li');

    if (message.messageType === 'ENTER') {
        messageElement.classList.add('event-message');
        message.chatContent = message.empName + ' joined!';
    } else if (message.messageType === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.chatContent = message.empName + ' left!';
    } else {
        messageElement.classList.add('chat-message');
        var usernameElement = document.createElement('strong');
        usernameElement.appendChild(document.createTextNode(message.empName));
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('span');
    var messageText = document.createTextNode(message.chatContent);
    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);

    document.querySelector('#messageArea').appendChild(messageElement);
}