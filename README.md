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
  <summary><b>File 구조</b></summary>
  <br>
  <div markdown="1">
    <ul>
      <img src="https://github.com/user-attachments/assets/6aceacfc-63cd-411b-8647-34e31701ea70"/>
    </ul>
  </div>
</details>

<details>
  <summary><b>기능 1. SpringBoot WebSocket을 활용한 채팅 기능</b></summary>
  <br>
  <div markdown="1">
    <ul>
      <img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2F1d0bb550-7972-42de-ab59-91f2272076c3%2FUntitled.png?table=block&id=38b10326-93c9-4e88-ad75-0c8a5f4216d9&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2"/>
      <em> 개인 채팅방의 경우 상단에 상대방의 상태 및 상태 메세지가 출력되며 그룹 채팅방의 경우 채팅방 이름, 채팅방 개설 일자가 출력됨 </em>
    </ul>
  </div>
</details>

<details>
  <summary><b>기능 2. 채팅 파일 전송 및 다운로드</b></summary>
  <br>
  <div markdown="1">
    <ul>
      <img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2F4d01c477-b459-487e-bdb4-f1496c8eea94%2FUntitled.png?table=block&id=80217174-0194-4071-a51c-b8450c0362a8&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2"/>
      <img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2Fce4790bb-b240-4691-bc95-e3bdbb1e3150%2FUntitled.png?table=block&id=39a795f6-8b6f-475c-9f17-b24c27ff1922&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2"/>
      <em> 채팅창에서 파일 전송 기능으로 사진의 경우 정규 표현식을 활용하여 미리보기 기능 구현, 모든 파일에 대해서는 클릭 시 다운로드 가능 </em>
    </ul>
  </div>
</details>

<details>
  <summary><b>기능 3. 그룹 채팅방 생성 기능</b></summary>
  <br>
  <div markdown="1">
    <ul>
     <img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2Fcc5fd719-59b3-4a44-aa76-25f888dab64c%2FUntitled.png?table=block&id=bf5e7d93-3f21-4b02-96bf-9152c004f19a&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2"/>
      <em> Bootstrap의 Modal 창을 활용하여 그룹 채팅방을 생성할 수 있는 기능 </em>
    </ul>
  </div>
</details>

<details>
  <summary><b>기능 4. 상태(Online, Offline, Meeting 등), 상태 메세지(사용자 지정 임의 메세지) 변경 기능</b></summary>
  <br>
  <div markdown="1">
    <ul>
     <img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2F87fcdfed-2b64-4d29-9c47-fe101e69d9a4%2FUntitled.png?table=block&id=b0916887-5331-41d1-8675-465712d4f316&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2"/>
<img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2Ff5a0be5d-586c-44d6-a9b4-9ef1a99de016%2FUntitled.png?table=block&id=88d66843-39ff-4ca6-9f06-1e62959db60e&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2"/>
<img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2F46bcca59-d211-47ef-b47c-ee9f0cc59911%2FUntitled.png?table=block&id=b6134ebe-a5ad-408b-a432-a5cbc64ad647&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2"/>
      <em> 상태 변경, 상태 메세지 변경 시 다른 사원들은 실시간으로 조회 가능(개인 채팅방 생성 시) </em>
    </ul>
  </div>
</details>

<details>
  <summary><b>기능 5. 사원 조회 및 채팅방 조회 기능</b></summary>
  <br>
  <div markdown="1">
    <ul>
      <table border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="50%">
            <img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2Fe00c6908-b563-42f8-8806-a771ce4acd66%2FUntitled.png?table=block&id=a5bdb407-c908-4a3a-8d32-8c08d964a3f7&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2" width="100%">
          </td>
          <td width="50%">
            <img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2Fa1365c08-4afb-4316-bfdd-fd1faa5c7fa6%2F4e8e18b5-89c8-4570-b845-8729a23e089a.png?table=block&id=7013bb3e-44fe-49f2-a6dd-3554018c3a67&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2" width="100%">
          </td>
        </tr>
      </table>
      <em> 검색한 글자(2글자 이상)가 포함되는 사원 전체 출력 및 채팅방 조회 </em>
    </ul>
  </div>
</details>

<details>
  <summary><b>기능 6. SSE(Server Sent Event)를 활용한 알림 기능</b></summary>
  <br>
  <div markdown="1">
    <ul>
    <img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2Fb390cb3e-0df8-4aa6-8888-f6fca84885a3%2FUntitled.png?table=block&id=09d3cf1f-4b11-449a-83ac-9422a22494ae&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2"/>
<img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2F41adab36-d5fd-4cde-ac21-110a8fedfe9e%2FUntitled.png?table=block&id=58630fe4-056f-48eb-bce4-095ac9abcd5f&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2"/>
      <em> 읽지 않은 채팅 내용에 대하여 알림을 보내주며 해당 글을 읽었을 때 알림창 숫자(1 → 0)가 변하는 모습 </em>
    </ul>
  </div>
</details>

<br>

### (2) 전자결재 조회 기능
<details>
  <summary><b>File 구조</b></summary>
  <br>
  <div markdown="1">
    <ul>
      <img src="https://github.com/user-attachments/assets/f0ad1bc8-1427-487c-b1fd-a6c387cc0775"/>
    </ul>
  </div>
</details>
<details>
  <summary><b>기능 1. 전체 전자 결재 문서 조회 기능</b></summary>
  <br>
  <div markdown="1">
    <ul>
    <img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2F620ba307-5b55-4fae-8757-75b2ba40ed93%2FUntitled.png?table=block&id=14091394-4263-469c-8ee9-dd74199bbde5&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2"/>
      <em> 로그인한 사원이 볼 수 있는 전체 전자 결재 문서 조회 기능 </em>
    </ul>
  </div>
</details>

<details>
  <summary><b>기능 2. 전자 결재 별 상세 보기 기능</b></summary>
  <br>
  <div markdown="1">
    <ul>
    <img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2F31c62faf-8d98-446a-bb55-32df105a3cc9%2FUntitled.png?table=block&id=f894288e-1bce-42a0-9702-b1f80b869809&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2"/>
      <em> 사원이 볼 수 있는 전체의 문서를 클릭 시 해당 상세보기 창으로 이동 </em>
    </ul>
  </div>
</details>

<details>
  <summary><b>기능 3. 그 외 내가 기안한 문서, (수신, 참조, 내가 받지는 않았으나 볼 수 있는 문서, 임시 저장한) 문서 조회</b></summary>
  <br>
  <div markdown="1">
    <ul>
    <img src="https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fa4dad6af-2889-4f24-849e-36828eac7662%2Fbcbcac08-57a8-48b3-a3a2-2724d5656c6c%2Fimage.png?table=block&id=f0b1830f-5f94-48f6-9a6c-22994bf15ad6&spaceId=a4dad6af-2889-4f24-849e-36828eac7662&width=2000&userId=17bae69d-6413-43c4-854c-4fa3895934cb&cache=v2"/>
      <em> 추가적으로 클릭 시 기능 2의 문서 상세보기 창으로 이동하여 상세 정보를 확인할 수 있음 </em>
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

