## ✋ 프로젝트 소개

<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%">
     <img src="https://i.ibb.co/4ptjDpC/image.gif"/>
    </td>
  </tr>
</table>

## 👨‍👨‍👧‍👧팀원 소개
<img src="https://i.ibb.co/N2zBWV5/image.gif"/>


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
#### 각 기능들을 클릭해주세요 !
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
    <img src="https://i.ibb.co/5TgQQ5F/image.gif"/> <br>
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
  <summary><b>기능 3. 게시글 수정 기능</b></summary>
  <br>
  <div markdown="1">
    <ul>
    <img src="https://i.ibb.co/N7Mhsqb/image.gif"/>
      <em> 작성자 본인만 수정 가능하도록 로직 처리 </em>
    </ul>
  </div>
</details>

<details>
  <summary><b>기능 3. 게시글 삭제 기능</b></summary>
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
  <summary><b>기능 1. 외부로 메일 발송</b></summary>
  <br>
  <div markdown="1">
    <ul>
      <img src="https://i.ibb.co/LxNs1Ch/image.gif"/>
      <img src="https://i.ibb.co/XpwVvyG/image.gif"/>
      <em> EmailJs Api를 사용하여 외부로 메일 발신 가능</em>
    </ul>
  </div>
</details>
<br>
<details>
  <summary><b>기능 2. 외부에서 받은 메일 수신</b></summary>
  <br>
  <div markdown="1">
    <ul>
      <img src="https://i.ibb.co/7j9pxvK/image.gif"/>
      <em> Gmail Api를 사용하여 생성해놓은 이메일 계정에 받은 메일 연동</em>
    </ul>
  </div>
</details>

## 📚 배운 점 & 아쉬운 점
### 해당 프로젝트에서 무엇을 배웠는가 ?
<ul align="justify">
  <li>
    <b>WebSocket 특성에 대한 이해</b> <br>
    <ul>
      <li>
        프로젝트 시작할 때에는 수업 때 배웠던 어려웠던 웹소켓을 정복하고 싶은 마음에 메신저를 맡겠다고 했지만, 기능 구현을 위해서 공부하면서 정말 많이 후회했습니다.
        여태까지 배웠던 서블릿, 스프링에서의 데이터의 흐름과는 다른 방식인 것 같아서 프로젝트가 시작되고서 한달동안은 수많은 블로그들을 돌아다니고 수업 때 배웠던 자료를 들여다보기만 했습니다.
        Stomp라는 프로토콜을 적용해서 코드를 작성해보았지만 웹소켓에 전반적인 이해가 없는 상태에서는 꽤나 버거운 방식이라 수업 때 배운 기본적인 웹소켓 통신으로 기능을 구현하기 시작했습니다.
        그래도 계속 들여다보고, 다른 조의 메신저 기능을 맡은 사람들에게 물어보며 웹소켓에 대한 이해가 조금씩 생기니 기능을 구현하기에는 큰 문제는 없었습니다. 혼자서는 힘든 일도 주변의 도움들이
        있다면 나아지는 모습을 보며 역시 개발은 협업이구나 라는 점을 제일 크게 배웠습니다.
      </li>
    </ul>
  </li>
  <br>
  <li>
    <b>가장 기본적인 CRUD에 대한 자신감</b> <br>
    <ul>
      <li>
        파이널 프로젝트를 시작하기 전의 약 4개월의 과정에서는 기본적인 CRUD에 대한 자신감이 없는 상태였지만, 이번 프로젝트의 공지사항 게시판 기능을 맡으면서 CRUD에 대한 자신감을 얻을 수 있었습니다. 
      </li>
    </ul>
  </li>
  <br>
  <li>
    <b>DB와 ERD설계</b> <br>
    <ul>
      <li>
        세미프로젝트를 할 때에도 DB를 담당하긴 했었지만, 세미프로젝트를 진행할 때에는 ERD 자체에도 수정할 부분이 많았고 수정이 필요한 설계서를 가지고 테이블을 생성하다보니 프로젝트를 진행하면서도
        정말 많은 수정 작업을 진행했었습니다. 이전 프로젝트의 경험을 살려서 보완해야할 부분들을 팀원들과 상의하여 ERD를 작성하였고 전보다는 제대로 작성된 설계서를 가지고 DB를 구축하다보니
        파이널 프로젝트에서는 전보다 적은 수정작업들로 프로젝트를 끝마칠 수 있었습니다. 데이터베이스를 만지다보니 슴슴한 매력을 느낄 수 있었던 기회여서, 추후 SQLD와 OCP를 취득할 예정입니다.
      </li>
    </ul>
  </li>
</ul>

### 아쉬웠던 사항 ?
<ul>
  <li>
    <b>웹메일 기능에서 Gmail Api에서 제공하는 제한적인 기능들로 완벽한 기능을 구현하지 못해서 아쉽습니다.</b> <br>
    ▶ 클라이언트 측에서 메일 label에 대한 수정은 제한되어 있어서 삭제, 스팸등과 같은 label을 수정하는 작업들을 완벽히 구현하지 못한 점이 아쉽습니다.
    추후 AWS를 통해서 배포한 이후에 Google측에 도메인을 넘겨 권한을 확장받고 기능을 완벽히 구현할 예정입니다.
  </li>
  <li>
    <b>Docker 및 AWS를 활용하여 나만의 Server를 구축하고 CI/CD를 직접 하지 못한 점이 아쉽습니다.</b> <br>
    ▶ 💪 현재 Server에 배포되어 있지 않은 상황이라 빠른 시일 내로 AWS를 배운 후 배포하도록 하겠습니다.
  </li>
</ul>

