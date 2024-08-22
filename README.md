## ✋ 프로젝트 소개

<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%">
     <img src="https://i.ibb.co/4ptjDpC/image.gif"/>
    </td>
  </tr>
</table>

## 👨‍👨‍👧‍👧팀원 소개
<img src="https://i.ibb.co/WK0jcb3/image.gif"/>

### 📅 개발 기간 : 2024.06.25 ~ 2024.08.06

<br>


<br>
<br>

## ⚙️ 기술 스택
<img src="https://i.ibb.co/9hK7XfS/image.gif" style="height: 300px;"/>

<br>


## 📃 WBS 및 ERD
<details>
  <summary><b>WBS</b></summary>
  <br>
  <div markdown="1">
    <ul>
      <img src="https://i.ibb.co/sbmCYMc/wbs.gif"/>
    </ul>
  </div>
</details>

<details>
  <summary><b>ERD</b></summary>
  <br>
  <div markdown="1">
    <ul>
      <img src="https://i.ibb.co/80XGpS6/erd.gif"/>
    </ul>
  </div>
</details>
<br>


## 🔧 구현 기능

### (1) 채팅 서비스
<details>
  <summary><b>기능 1. SpringBoot WebSocket을 활용한 채팅 기능</b></summary>
  <br>
  <div markdown="1">
    <ul>
      <img src="https://i.ibb.co/JpWTj0J/image.gif"/>
      <em> 메세지를 전송했을 경우, 수신자와 발신자 모두에게 채팅방 생성 및 실시간 채팅 가능 </em>
    </ul>
  </div>
</details>
<br>

### (2) 공지사항 게시판
<details>
  <summary><b>기능 1. 게시글 작성 기능</b></summary>
  <br>
  <div markdown="1">
    <ul>
    <img src="https://i.ibb.co/5TgQQ5F/image.gif"/>
      <em> 공지사항 게시판에 게시글 작성 가능 </em>
    </ul>
  </div>
</details>

<details>
  <summary><b>기능 2. 게시글 조회 기능</b></summary>
  <br>
  <div markdown="1">
    <ul>
    <img src="https://i.ibb.co/MsZ0YMV/image.gif"/>
    <img src="https://i.ibb.co/PMBVmdt/image.gif"/>
      <em> 모든 사원이 볼 수 있는 게시글 리스트 조회 </em>
    </ul>
  </div>
</details>

<details>
  <summary><b>기능 3. 게시글 수정 기능능</b></summary>
  <br>
  <div markdown="1">
    <ul>
    <img src="https://i.ibb.co/N7Mhsqb/image.gif"/>
      <em> 작성자 본인만 수정 가능하도록 로직 처리 </em>
    </ul>
  </div>
</details>

<details>
  <summary><b>기능 3. 게시글 삭제 기능능</b></summary>
  <br>
  <div markdown="1">
    <ul>
    <img src="https://i.ibb.co/zmt91X4/image.gif"/>
    <img src="https://i.ibb.co/VH6Jgfv/image.gif"/>
      <em> 작성자 본인만 삭제 가능하도록 로직 처리 </em>
    </ul>
  </div>
</details>
<br>
### (3) 웹메일 서비스
<details>
  <summary><b>기능 1. SpringBoot WebSocket을 활용한 채팅 기능</b></summary>
  <br>
  <div markdown="1">
    <ul>
      <img src="https://i.ibb.co/JpWTj0J/image.gif"/>
      <em> 메세지를 전송했을 경우, 수신자와 발신자 모두에게 채팅방 생성 및 실시간 채팅 가능 </em>
    </ul>
  </div>
</details>
<br>


## 📚 배운 점 & 아쉬운 점
### 해당 프로젝트에서 무엇을 배웠는가 ?
<ul align="justify">
  <li>
    <b>WebSocket 특성 상 Data의 관리의 중요성</b> <br>
    <ul>
      <li>
        실시간으로 데이터를 처리해주고 양방향 통신을 해야하는 만큼 Data를 어떻게 효율적으로 처리할 것 인가에 대하여 수많은 고민을 하였고 그 결과 현업에서는 응답 데이터 전송용 DTO와 요청 데이터 전송용 DTO 및 여러 서비스에서 사용하는 Common DTO로 나뉜다는 내용을 알게 되었고 ERD를 기반으로 DTO 클래스를 추가하였습니다.
      </li>
      <li>
        그 결과, 원하는 정보를 온전히 가져올 수 있었으며 Component의 재사용성 또한 높아졌으며 각 Method 별 Module화가 원활해질 수 있었습니다. <br>  
      </li>
      <li>
        실제 구현한 응답 데이터 전송 객체<br>
        <img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2F6dd6d38c-7835-4b14-ba02-7dbedc282945%2FUntitled.png?table=block&id=b998de7a-8f4e-4dd3-9e8f-facb31f08caf&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2"/>
        <em>Client 측에서 보낸 Object를 parsing하여 Business Logic을 처리 후 응답하는 용도의 DTO</em>
      </li>
    </ul>
  </li>
  <br>
  <li>
    <b>능동적으로 학습하여 구현할 수 있다는 사실</b> <br>
    <ul>
      <li>
        국비지원 수업 과정 중 다루지 않았던 Server Sent Event(SSE)에 대하여 스스로 찾아보고 WebSocket과의 차이점을 공부하며 어떠한 경우에 SSE를 사용하는 것이 바람직하며 이로 인한 이점은 무엇이 있는지 찾아보고 난 후 Business Logic을 구현하였습니다.
      </li>
      <li>
        관련된 Velog 글 : 🔗<a href="https://velog.io/@lunia1109/TIL-24.07.21">운영 중인 Velog</a>
      </li>
    </ul>
  </li>
  <br>
  <li>
    <b>UX 기반의 UI 설계</b> <br>
    <ul>
      <li>
        내가 사용자라면 과연 해당 채팅 서비스를 이용할 것인가에 대한 고민을 하며 비교적 쾌적하며 더욱 많은 UI 설계를 위하여 기능 추가 및 레이아웃을 재설계 하였습니다.
      </li>
      <li>
        프로젝트 돌입 전 연습으로 구현했었던 채팅 서비스 UI<br>
          <img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2F249bd34d-e6b7-49c2-b7c5-fa7f2cb15adb%2FUntitled.png?table=block&id=89215f5f-b1f2-4b94-b37d-39ca6a6d70c4&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2"/>
        <em>실제 채팅 서비스를 구현한다는 생각으로 프로젝트 시작 전 연습용으로 구현한 채팅 서비스 UI</em>
      </li>
    </ul>
  </li>
</ul>

### 아쉬웠던 사항 ?
<ul>
  <li>
    <b>채팅 기능 특성 상 Server의 부담이 너무 큰 점을 해결하지 못한 점이 아쉬웠습니다.</b> <br>
    ▶ 이후에 캐싱 처리를 통한 DB 처리량의 완화를 계획 중에 있습니다.
  </li>
  <li>
    <b>초반에 잘못 설계한 DTO로 인한 부족한 성능을 향상시키지 못하여 아쉽습니다.</b> <br>
    ▶ 프로젝트 발표 이후 독자적으로 채팅 서비스를 구현할 예정으로 Data 정규화 및 DTO 재설계를 처음부터 할 예정입니다.
  </li>
  <li>
    <b>Docker 및 AWS를 활용하여 나만의 Server를 구축하고 CI/CD를 직접 하지 못한 점이 아쉽습니다.</b> <br>
    ▶ 스스로 구축한 채팅 서비스의 경우 부족한 Docker 및 AWS 지식을 습득한 후 구현해 볼 예정입니다.<br>
    ▶ 💪 현재 Server에 배포되어 있지 않은 상황이라 빠른 시일 내로 AWS를 배운 후 배포하도록 하겠습니다.
  </li>
</ul>

