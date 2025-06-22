<script lang="ts">
  let newCustomer = {
    customer_name: '', // 닉네임
    address: '',
    email: '',
    phone_number: ''
  };

  let message = '';

  // 닉네임 중복 여부 확인 함수
  async function isDuplicateNickname(nickname: string): Promise<boolean> {
    try {
      const res = await fetch(`http://localhost:8080/api/customerstest`);
      if (!res.ok) return false;

      const customers = await res.json();
      return customers.some((c: any) => c.customer_name === nickname);
    } catch (err) {
      console.error('❌ 닉네임 중복 확인 중 오류 발생:', err);
      return false;
    }
  }

  // 회원 추가 함수
  async function addCustomer() {
    const isDuplicate = await isDuplicateNickname(newCustomer.customer_name);
    if (isDuplicate) {
      message = '⚠ 이미 존재하는 닉네임입니다. 다른 닉네임을 입력하세요.';
      return;
    }

    try {
      const res = await fetch('http://localhost:8080/api/customerstest', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newCustomer)
      });

      if (res.ok) {
        message = '✅ 회원이 성공적으로 추가되었습니다!';
        newCustomer = {
          customer_name: '',
          address: '',
          email: '',
          phone_number: ''
        };
      } else {
        message = '❌ 회원 추가에 실패했습니다.';
      }
    } catch (err) {
      console.error(err);
      message = '⚠️ 서버 오류가 발생했습니다.';
    }
  }
</script>

<main class="add-customer-container">
  <h2>📌 회원 추가</h2>
  <form class="customer-form" on:submit|preventDefault={addCustomer}>
    <div class="form-group">
      <label for="customer_name">닉네임</label>
      <input
        id="customer_name"
        bind:value={newCustomer.customer_name}
        placeholder="닉네임을 입력하세요"
        required
      />
    </div>

    <div class="form-group">
      <label for="address">주소</label>
      <input
        id="address"
        bind:value={newCustomer.address}
        placeholder="주소를 입력하세요"
        required
      />
    </div>

    <div class="form-group">
      <label for="email">이메일</label>
      <input
        id="email"
        type="email"
        bind:value={newCustomer.email}
        placeholder="이메일을 입력하세요"
        required
      />
    </div>

    <div class="form-group">
      <label for="phone_number">전화번호</label>
      <input
        id="phone_number"
        type="tel"
        bind:value={newCustomer.phone_number}
        placeholder="전화번호를 입력하세요"
        required
      />
    </div>

    <button type="submit" class="submit-btn">회원 추가</button>
  </form>

  {#if message}
    <p class="message">{message}</p>
  {/if}
</main>

<style>
  .add-customer-container {
    max-width: 600px;
    margin: 40px auto;
    padding: 30px;
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
  }

  h2 {
    font-size: 1.8rem;
    text-align: center;
    margin-bottom: 25px;
    color: #2c3e50;
  }

  .customer-form {
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
    margin-bottom: 6px;
    color: #333;
  }

  input {
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 8px;
    font-size: 1rem;
    outline: none;
  }

  input:focus {
    border-color: #4a90e2;
  }

  .submit-btn {
    background-color: #2c3e50;
    color: #fff;
    padding: 14px;
    border: none;
    border-radius: 8px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  .submit-btn:hover {
    background-color: #1a2a3a;
  }

  .message {
    margin-top: 20px;
    text-align: center;
    font-weight: 600;
    color: #e74c3c;
  }
</style>
