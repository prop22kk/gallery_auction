<main class="contact-container">
  <h1>고객센터 문의</h1>
  <form class="contact-form">
    <div class="form-group">
      <label for="inquiryTitle">문의 제목</label>
      <input type="text" id="inquiryTitle" name="inquiryTitle" placeholder="예: 로그인 오류가 발생합니다" required />
    </div>

    <div class="form-group">
      <label for="email">이메일 주소</label>
      <input type="email" id="email" name="email" placeholder="예: example@email.com" required />
    </div>

    <div class="form-group">
      <label for="message">문의 내용</label>
      <textarea id="message" name="message" rows="6" placeholder="문의 내용을 입력해 주세요..." required></textarea>
    </div>

    <button type="submit" class="submit-btn">문의 보내기</button>
  </form>
</main>

<style>
  .contact-container {
    max-width: 800px;
    margin: 40px auto;
    padding: 30px;
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
  }

  .contact-container h1 {
    font-size: 2rem;
    margin-bottom: 20px;
    text-align: center;
    color: #2c3e50;
  }

  .contact-form {
    display: flex;
    flex-direction: column;
    gap: 20px;
  }

  .form-group {
    display: flex;
    flex-direction: column;
  }

  label {
    font-weight: 600;
    margin-bottom: 8px;
    color: #333;
  }

  input[type="text"],
  input[type="email"],
  textarea {
    padding: 12px;
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 8px;
    outline: none;
  }

  input:focus,
  textarea:focus {
    border-color: #4a90e2;
  }

  .submit-btn {
    background-color: #2c3e50;
    color: white;
    border: none;
    padding: 14px;
    border-radius: 8px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  .submit-btn:hover {
    background-color: #1a2a3a;
  }
</style>
