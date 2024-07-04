<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
   <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat Interface</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        .container {
            display: flex;
            height: 100vh;
        }
        .sidebar {
            width: 300px;
            background-color: #fff;
            box-shadow: 2px 0 5px rgba(0,0,0,0.1);
            display: flex;
            flex-direction: column;
            overflow: hidden;
        }
        .sidebar .search {
            padding: 20px;
            border-bottom: 1px solid #eee;
        }
        .sidebar .search input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .sidebar .chats, .sidebar .contacts {
            flex: 1;
            overflow-y: auto;
        }
        .sidebar .chats .chat, .sidebar .contacts .contact {
            padding: 15px 20px;
            border-bottom: 1px solid #eee;
            display: flex;
            align-items: center;
            cursor: pointer;
        }
        .sidebar .chats .chat.active, .sidebar .contacts .contact:hover {
            background-color: #f5f5f5;
        }
        .sidebar .chats .chat img, .sidebar .contacts .contact img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            margin-right: 15px;
        }
        .sidebar .chats .chat .details, .sidebar .contacts .contact .details {
            flex: 1;
        }
        .sidebar .chats .chat .details .name, .sidebar .contacts .contact .details .name {
            font-weight: bold;
            margin-bottom: 5px;
        }
        .sidebar .chats .chat .details .message, .sidebar .contacts .contact .details .status {
            color: #888;
            font-size: 14px;
        }
        .main {
            flex: 1;
            display: flex;
            flex-direction: column;
            background-color: #fff;
        }
        .main .header {
            padding: 20px;
            border-bottom: 1px solid #eee;
            display: flex;
            align-items: center;
        }
        .main .header img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            margin-right: 15px;
        }
        .main .header .details {
            flex: 1;
        }
        .main .header .details .name {
            font-weight: bold;
        }
        .main .chat-area {
            flex: 1;
            padding: 20px;
            overflow-y: auto;
            background-color: #f9f9f9;
        }
        .main .chat-area .message {
            display: flex;
            margin-bottom: 20px;
        }
        .main .chat-area .message.sent {
            justify-content: flex-end;
        }
        .main .chat-area .message .content {
            max-width: 60%;
            padding: 15px;
            border-radius: 15px;
            position: relative;
        }
        .main .chat-area .message.sent .content {
            background-color: #4f92ff;
            color: #fff;
            border-bottom-right-radius: 0;
        }
        .main .chat-area .message.received .content {
            background-color: #fff;
            border: 1px solid #ddd;
            border-bottom-left-radius: 0;
        }
        .main .chat-area .message .content .time {
            font-size: 12px;
            color: #888;
            position: absolute;
            bottom: -20px;
            right: 15px;
        }
        .main .chat-input {
            padding: 20px;
            border-top: 1px solid #eee;
            display: flex;
            align-items: center;
        }
        .main .chat-input input {
            flex: 1;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-right: 10px;
        }
        .main .chat-input button {
            background-color: #4f92ff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="sidebar">
            <div class="search">
                <input type="text" placeholder="Search...">
            </div>
            <div class="chats">
                <div class="chat active">
                    <img src="https://randomuser.me/api/portraits/women/1.jpg" alt="User">
                    <div class="details">
                        <div class="name">Felecia Rower</div>
                        <div class="message">I will purchase it for...</div>
                    </div>
                </div>
                <div class="chat">
                    <img src="https://randomuser.me/api/portraits/men/2.jpg" alt="User">
                    <div class="details">
                        <div class="name">Waldemar Mannering</div>
                        <div class="message">Refer friends. Get rew...</div>
                    </div>
                </div>
                <div class="chat">
                    <img src="https://randomuser.me/api/portraits/men/3.jpg" alt="User">
                    <div class="details">
                        <div class="name">Calvin Moore</div>
                        <div class="message">If it takes long you can ma...</div>
                    </div>
                </div>
            </div>
            <div class="contacts">
                <div class="contact">
                    <img src="https://randomuser.me/api/portraits/women/4.jpg" alt="User">
                    <div class="details">
                        <div class="name">Natalie Maxwell</div>
                        <div class="status">UI/UX Designer</div>
                    </div>
                </div>
                <div class="contact">
                    <img src="https://randomuser.me/api/portraits/men/5.jpg" alt="User">
                    <div class="details">
                        <div class="name">Jess Cook</div>
                        <div class="status">Business Analyst</div>
                    </div>
                </div>
                <div class="contact">
                    <img src="https://randomuser.me/api/portraits/men/6.jpg" alt="User">
                    <div class="details">
                        <div class="name">Louie Mason</div>
                        <div class="status">Resource Manager</div>
                    </div>
                </div>
                <div class="contact">
                    <img src="https://randomuser.me/api/portraits/women/7.jpg" alt="User">
                    <div class="details">
                        <div class="name">Krystal Norton</div>
                        <div class="status">Business Executive</div>
                    </div>
                </div>
                <div class="contact">
                    <img src="https://randomuser.me/api/portraits/women/8.jpg" alt="User">
                    <div class="details">
                        <div class="name">Stacy Garrison</div>
                        <div class="status">Marketing Ninja</div>
                    </div>
                </div>
                <div class="contact">
                    <img src="https://randomuser.me/api/portraits/men/9.jpg" alt="User">
                    <div class="details">
                        <div class="name">Calvin Moore</div>
                        <div class="status">UX Engineer</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="main">
            <div class="header">
                <img src="https://randomuser.me/api/portraits/women/1.jpg" alt="User">
                <div class="details">
                    <div class="name">Felecia Rower</div>
                    <div class="status">NextJS developer</div>
                </div>
            </div>
            <div class="chat-area">
                <div class="message received">
                    <div class="content">
                        How can I purchase it?
                        <div class="time">10:05 AM</div>
                    </div>
                </div>
                <div class="message sent">
                    <div class="content">
                        Thanks, you can purchase it.
                        <div class="time">10:06 AM</div>
                    </div>
                </div>
                <div class="message sent">
                    <div class="content">
                        Great, Feel free to get in touch.
                        <div class="time">10:10 AM</div>
                    </div>
                </div>
                <div class="message received">
                    <div class="content">
                        I will purchase it for sure. 👍
                        <div class="time">10:08 AM</div>
                    </div>
                </div>
                <div class="message received">
                    <div class="content">
                        Do you have design files for Sneat?
                        <div class="time">10:15 AM</div>
                    </div>
                </div>
                <div class="message sent">
                    <div class="content">
                        Yes that's correct documentation file, Design files are included with the template.
                        <div class="time">10:15 AM</div>
                    </div>
                </div>
            </div>
            <div class="chat-input">
                <input type="text" placeholder="Type your message here...">
                <button>Send</button>
            </div>
        </div>
    </div>
</body>
   <div class="container-xxl flex-grow-1 container-p-y">
   </div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>