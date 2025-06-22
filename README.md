# 🎨 Gallery Auction

### 온라인 예술 작품 경매 시스템

---

## 📌 프로젝트 개요

**Gallery Auction**은 사용자가 온라인으로 예술 작품을 경매에 등록하고, 입찰 및 낙찰까지의 과정을 웹을 통해 진행할 수 있는 예술 경매 플랫폼입니다.

---

## 👥 프로젝트 구성원 및 역할

- **김지현**: 회원관리 API(Svelte 연동), 프론트엔드 & 백엔드 연결, 기능 추가  
- **박소영**: 프론트엔드 설계 및 구현  
- **이지영**: PostgreSQL DB 설계, 백엔드 구현 

---

## 🛠️ 사용 기술 스택

- **Backend**: Java, Spring Boot, Spring Web, Spring JDBC  
- **Frontend**: Svelte (VSCode 환경)  
- **DB**: PostgreSQL  
- **테스트 및 연동 도구**: Postman  
- **개발 도구**: IntelliJ IDEA, Visual Studio Code

---

## 📦 데이터베이스 설계
---

### 🗂️ 테이블 정의

#### 🧍 customer (회원)
| 컬럼명             | 타입             | 제약조건               | 설명       |
| --------------- | -------------- | ------------------ | -------- |
| `customer_id`   | `serial`       | `PRIMARY KEY`      | 회원 고유 ID |
| `address`       | `varchar(100)` | `UNIQUE, NOT NULL` | 주소       |
| `phone_number`  | `varchar(20)`  | `UNIQUE, NOT NULL` | 전화번호     |
| `email`         | `varchar(100)` | `UNIQUE, NOT NULL` | 이메일 주소   |
| `customer_name` | `varchar(20)`  | `UNIQUE, NOT NULL` | 회원 이름    |


#### 🖼️ items (작품)
| 컬럼명              | 타입              | 제약조건                                   | 설명           |
| ---------------- | --------------- | -------------------------------------- | ------------ |
| `item_id`        | `serial`        | `PRIMARY KEY`                          | 작품 고유 ID     |
| `artist`         | `varchar(100)`  | `NOT NULL`                             | 작가명          |
| `item_name`      | `varchar(100)`  | `NOT NULL`                             | 작품 이름        |
| `starting_price` | `numeric(12,2)` | `NOT NULL`                             | 시작 가격        |
| `auction_start`  | `timestamp`     | `NOT NULL`                             | 경매 시작 시간     |
| `auction_end`    | `timestamp`     | `NOT NULL`                             | 경매 종료 시간     |
| `category`       | `varchar(100)`  |                                        | 카테고리         |
| `owner_id`       | `varchar`       | `FOREIGN KEY → customer.customer_name` | 소유자 이름 (회원명) |
| `current_price`  | `numeric(12,2)` | `DEFAULT 0`                            | 현재 최고가       |
| `image_url`      | `varchar(255)`  |                                        | 이미지 경로       |


#### 🧾 activities (입찰 기록)
| 컬럼명             | 타입              | 제약조건                                   | 설명           |
| --------------- | --------------- | -------------------------------------- | ------------ |
| `activity_id`   | `serial`        | `PRIMARY KEY`                          | 입찰 기록 고유 ID  |
| `member_id`     | `varchar`       | `FOREIGN KEY → customer.customer_name` | 입찰자 이름 (회원명) |
| `item_id`       | `integer`       | `FOREIGN KEY → items.item_id`          | 입찰한 작품 ID    |
| `price`         | `numeric(12,2)` |                                        | 입찰 금액        |
| `activity_time` | `timestamp`     | `DEFAULT CURRENT_TIMESTAMP`            | 입찰 시간        |


### 📊 ERD 다이어그램

![image](https://github.com/user-attachments/assets/3a884105-34af-4680-9a56-a77dd5e1e667)


---

## 👤 회원 관리 기능

### 1. 회원 등록

닉네임, 주소, 이메일, 전화번호를 입력하여 회원 등록 가능  

![image](https://github.com/user-attachments/assets/703ddaee-cee7-4b60-a593-bfbe35a0ca2d)


1-(1) 중복 확인
닉네임이 중복시 이미 존재하는 닉네임이라는 알림이 뜨며 중복 닉네임이 불가

![image](https://github.com/user-attachments/assets/7ad4d65d-910f-4246-8fc2-f3ddd290053b)



---

### 2. 전체 회원 목록 조회

등록된 모든 회원 정보를 테이블로 확인  

![image](https://github.com/user-attachments/assets/8dacf69c-b220-4580-9ad9-d1139709a8ba)


---

### 3. 회원 ID로 검색

ID 입력 시 특정 회원 정보 조회  
![image](https://github.com/user-attachments/assets/e8836d60-0e2a-4cdf-a4e7-4e5135729ae3)


---

### 4. 회원 삭제

ID를 입력하면 삭제 확인 메시지가 팝업에 뜸

![image](https://github.com/user-attachments/assets/d6977448-4d32-4d89-b7f0-dd10b75cb10e)

삭제 대상 회원 정보와 함께 회원 삭제 성공 여부가 알림으로 뜨게됨

![image](https://github.com/user-attachments/assets/87332c88-4a99-4380-9a24-241bd4349163)

ID가 존재하지 않으면 회원 정보를 찾을 수 없다는 알림이 뜨게 됨

![image](https://github.com/user-attachments/assets/bfcb2a11-5f0e-45cb-81da-5edae8403529)

ID 입력 후 삭제 → 목록에서도 제거됨  
![image](https://github.com/user-attachments/assets/fadc2fc7-e0b7-412b-8b3d-6156ec1f2d93)




---

### 5. 회원 정보 수정

닉네임, 주소, 이메일, 전화번호를 수정 가능  

<strong>수정 전</strong> <strong>과</strong> <strong>수정 후</strong><br>
<img src="https://github.com/user-attachments/assets/7b73a419-0875-4f6a-b127-12c0d3adb86f" width="300"/>
<img src="https://github.com/user-attachments/assets/4ab88527-9ea2-46b7-9948-281ad691b1ea" width="300"/>

ID가 존재하지 않으면 회원 정보를 찾을 수 없다는 알림이 뜨게 됨
![image](https://github.com/user-attachments/assets/66d44ea0-6b92-414b-a00b-3e30be37cec3)

5-(1) 중복 아이디 수정 불가능
아이디가 중복되게 설정하면 중복된 아이디라는 문구를 띄워 중복 아이디 설정이 불가능하다.
![image](https://github.com/user-attachments/assets/0fcf7174-cf6f-4a9c-ac6c-49402a83c9de)


---

> ✅ 회원 관리 기능은 Svelte 프론트엔드와 Spring Boot 백엔드 연동을 통해 완성되었습니다.

---

## 🖼️ 작품 등록 및 경매 기능

### 1. 경매 작품 등록

이미지 업로드와 함께 작가명, 제목, 시작가, 기간, 카테고리, 소유자 닉네임 등 입력하여 등록  
![image](https://github.com/user-attachments/assets/fd0f37b2-e262-4021-82ec-8bf77db5f2a5)


---

### 2. 작품 갤러리 조회

등록된 작품을 갤러리 형태로 출력, 진행 중/종료됨 경매 필터  
![작품 갤러리](https://github.com/user-attachments/assets/46dbdf39-6124-47dc-a0c0-fc16e3c352f1)

---

### 3. 경매 참여 기능

- 입찰자는 소유자와 달라야 함  
- 입찰 금액은 현재 최고가 이상  
- 같은 사용자의 연속 입찰은 불가 (다른 사용자의 입찰 후 가능)

작품 상세 페이지에서 입찰 가능  
![image](https://github.com/user-attachments/assets/1af7deac-240e-4707-8aee-6f12105baaa6)


---

### 4. 경매 종료 및 낙찰 결과

경매가 종료되면 낙찰자 ID와 낙찰 금액 확인  
![image](https://github.com/user-attachments/assets/70f6eb44-2170-455d-818d-6b2df002392a)


---

> ✅ 작품 등록부터 낙찰까지 전체 흐름은 Svelte + Spring Boot 연동으로 구현 완료

---

## 📋 고객 활동 기록

### 1. 전체 고객 활동 조회

입찰 시간, 회원 ID, 물품 ID, 입찰 금액이 실시간으로 기록되어 전체 내역 확인 가능  

![image](https://github.com/user-attachments/assets/64c9e9bb-f544-4496-a748-f8d3ecfead86)


---

### 2. 특정 회원 활동 조회

회원 ID로 검색 시 해당 고객의 모든 활동 기록 조회  
![image](https://github.com/user-attachments/assets/e7280960-5458-49d2-85d1-bb1c57c04b36)



---

> ✅ 입찰 히스토리 및 고객별 활동 조회 기능을 통해 데이터 기반 응대 및 분석이 가능합니다.




