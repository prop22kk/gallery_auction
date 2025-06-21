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

### 📊 ERD 다이어그램

![image](https://github.com/user-attachments/assets/b78e8534-eb84-49d3-8635-87b86fe77023)


---

### 🗂️ 테이블 정의

#### 🧍 customer (회원)
| 컬럼명       | 타입         | 제약조건           | 설명       |
|--------------|--------------|--------------------|------------|
| customer_id  | SERIAL       | PRIMARY KEY        | 회원 고유 ID |
| address      | VARCHAR(100) | UNIQUE, NOT NULL   | 주소        |
| phone_number | VARCHAR(20)  | UNIQUE, NOT NULL   | 전화번호     |
| email        | VARCHAR(100) | UNIQUE, NOT NULL   | 이메일 주소  |

#### 🖼️ items (작품)
| 컬럼명         | 타입          | 제약조건                             | 설명           |
|----------------|---------------|--------------------------------------|----------------|
| item_id        | SERIAL        | PRIMARY KEY                          | 작품 고유 ID    |
| artist         | VARCHAR(100)  | NOT NULL                             | 작가명          |
| item_name      | VARCHAR(100)  | NOT NULL                             | 작품 제목       |
| starting_price | NUMERIC(12,2) | NOT NULL                             | 시작 가격       |
| auction_start  | TIMESTAMP     | NOT NULL                             | 경매 시작일     |
| auction_end    | TIMESTAMP     | NOT NULL                             | 경매 종료일     |
| category       | VARCHAR(100)  |                                      | 카테고리        |
| owner_id       | INTEGER       | FOREIGN KEY → customer(customer_id)  | 소유자 회원 ID  |
| current_price  | NUMERIC(12,2) | DEFAULT 0                            | 현재 최고가     |
| image_url      | VARCHAR(255)  |                                      | 이미지 경로      |

#### 🧾 activities (입찰 기록)
| 컬럼명        | 타입          | 제약조건                             | 설명             |
|---------------|---------------|--------------------------------------|------------------|
| activity_id   | SERIAL        | PRIMARY KEY                          | 활동 고유 ID      |
| member_id     | INTEGER       | FOREIGN KEY → customer(customer_id)  | 입찰자 회원 ID     |
| item_id       | INTEGER       | FOREIGN KEY → items(item_id)         | 입찰한 작품 ID     |
| price         | NUMERIC(12,2) |                                      | 입찰 금액         |
| activity_time | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP            | 입찰 시간         |

---

## 👤 회원 관리 기능

### 1. 회원 등록

주소, 이메일, 전화번호를 입력하여 회원 등록 가능  

![g1](https://github.com/user-attachments/assets/c75a3778-f47e-4d12-b8df-4e5ae5c2ef16)

---

### 2. 전체 회원 목록 조회

등록된 모든 회원 정보를 테이블로 확인  

![g2](https://github.com/user-attachments/assets/30a30e1a-973e-470c-874e-835624202260)

---

### 3. 회원 ID로 검색

ID 입력 시 특정 회원 정보 조회  
![회원 검색](https://github.com/user-attachments/assets/4f83f140-9e29-45b7-9b29-de13ed89a1bb)

---

### 4. 회원 삭제

ID 입력 후 삭제 → 목록에서도 제거됨  
![회원 삭제 전](https://github.com/user-attachments/assets/deb28bd6-1575-456c-aeb3-9f710eea9584)  
![회원 삭제 후](https://github.com/user-attachments/assets/73ef65c2-f766-4425-be4f-d6d0cc8fac2f)

---

### 5. 회원 정보 수정

주소, 이메일, 전화번호를 수정 가능  
![회원 수정](https://github.com/user-attachments/assets/118ba1e0-111e-40c1-ac8e-caed43623a95)

---

> ✅ 회원 관리 기능은 Svelte 프론트엔드와 Spring Boot 백엔드 연동을 통해 완성되었습니다.

---

## 🖼️ 작품 등록 및 경매 기능

### 1. 경매 작품 등록

이미지 업로드와 함께 작가명, 제목, 시작가, 기간, 카테고리, 소유자 ID 등 입력하여 등록  
![작품 등록](https://github.com/user-attachments/assets/185a34f3-4dc0-431a-882e-631b5c259eb3)

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
![입찰 페이지](https://github.com/user-attachments/assets/d1da1b63-7bb9-4ea4-b3fc-ba371b649366)

---

### 4. 경매 종료 및 낙찰 결과

경매가 종료되면 낙찰자 ID와 낙찰 금액 확인  
![낙찰 결과](https://github.com/user-attachments/assets/29f3de0d-0c9c-4add-b8df-858f64885d95)

---

> ✅ 작품 등록부터 낙찰까지 전체 흐름은 Svelte + Spring Boot 연동으로 구현 완료

---

## 📋 고객 활동 기록

### 1. 전체 고객 활동 조회

입찰 시간, 회원 ID, 물품 ID, 입찰 금액이 실시간으로 기록되어 전체 내역 확인 가능  

![tt1](https://github.com/user-attachments/assets/a2d53a10-d1f0-46f5-a4d3-b70524e61428)

---

### 2. 특정 회원 활동 조회

회원 ID로 검색 시 해당 고객의 모든 활동 기록 조회  
![tt2](https://github.com/user-attachments/assets/985be4c1-8e79-4c4b-8af2-f85cd4576d9b)


---

> ✅ 입찰 히스토리 및 고객별 활동 조회 기능을 통해 데이터 기반 응대 및 분석이 가능합니다.




