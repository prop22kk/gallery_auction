<script lang="ts">
  let customerIdToDelete = '';
  let customer: any = null;
  let message = '';
  let error = '';

  async function fetchCustomerBeforeDelete() {
    if (!customerIdToDelete) {
      error = '회원 ID를 입력하세요.';
      message = '';
      customer = null;
      return;
    }

    try {
      const res = await fetch(`http://localhost:8080/api/customerstest/${customerIdToDelete}`);
      if (!res.ok) {
        error = '해당 ID의 회원을 찾을 수 없습니다.';
        customer = null;
        return;
      }

      customer = await res.json();
      error = '';
      message = '';

      const confirmDelete = confirm(`정말로 ${customerIdToDelete} 회원을 삭제하시겠습니까?`);
      if (confirmDelete) {
        await deleteCustomer(customerIdToDelete);
      } else {
        message = '삭제가 취소되었습니다.';
      }

    } catch (err) {
      error = '서버 오류가 발생했습니다.';
      customer = null;
    }
  }

  async function deleteCustomer(id: string) {
    try {
      const res = await fetch(`http://localhost:8080/api/customerstest/${id}`, {
        method: 'DELETE',
      });

      if (!res.ok) {
        const errText = await res.text();
        error = `삭제 실패: ${errText}`;
        message = '';
        return;
      }

      message = '회원 삭제가 성공적으로 완료되었습니다.';
      error = '';
      // customer = null;
      customerIdToDelete = '';
    } catch (err) {
      error = '서버 오류가 발생했습니다.';
      message = '';
    }
  }
</script>

<main class="delete-container">
  <h1>회원 삭제</h1>
  <form class="delete-form" on:submit|preventDefault={fetchCustomerBeforeDelete}>
    <div class="form-group">
      <label for="memberId">회원 ID</label>
      <input
        type="text"
        id="memberId"
        name="memberId"
        bind:value={customerIdToDelete}
        placeholder="예: 2"
        required
      />
    </div>
    <button type="submit" class="delete-btn">회원 삭제</button>
  </form>

  {#if customer}
    <div class="customer-info">
      <h3>삭제 대상 회원 정보</h3>
      <p><strong>ID:</strong> {customer.id}</p>
      <p><strong>주소:</strong> {customer.address}</p>
      <p><strong>전화번호:</strong> {customer.phone_number}</p>
      <p><strong>이메일:</strong> {customer.email}</p>
    </div>
  {/if}

  {#if message}
    <p class="message success">{message}</p>
  {/if}
  {#if error}
    <p class="message error">{error}</p>
  {/if}
</main>

<style>
  .delete-container {
    max-width: 600px;
    margin: 40px auto;
    padding: 30px;
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
  }

  .delete-container h1 {
    font-size: 2rem;
    margin-bottom: 20px;
    text-align: center;
    color: #c0392b;
  }

  .delete-form {
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

  input[type="text"] {
    padding: 12px;
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 8px;
    outline: none;
  }

  input[type="text"]:focus {
    border-color: #e74c3c;
  }

  .delete-btn {
    background-color: #e74c3c;
    color: white;
    border: none;
    padding: 14px;
    border-radius: 8px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  .delete-btn:hover {
    background-color: #c0392b;
  }

  .customer-info {
    margin-top: 30px;
    background: #f9f9f9;
    padding: 20px;
    border-radius: 8px;
  }

  .customer-info h3 {
    margin-bottom: 10px;
    color: #333;
  }

  .customer-info p {
    margin: 6px 0;
    font-size: 0.95rem;
  }

  .message {
    text-align: center;
    margin-top: 20px;
    font-size: 1rem;
    font-weight: bold;
  }

  .message.success {
    color: green;
  }

  .message.error {
    color: red;
  }
</style>
