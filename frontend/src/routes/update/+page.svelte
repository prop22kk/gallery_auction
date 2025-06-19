<script>
  import { onMount } from 'svelte';

  let form;
  let memberIdInput;
  let addressInput;
  let emailInput;
  let phoneInput;

  onMount(() => {
    memberIdInput.addEventListener("change", async () => {
      const id = memberIdInput.value;
      if (!id) return;

      try {
        const res = await fetch(`/api/customerstest/${id}`);
        if (!res.ok) {
          alert("해당 회원 정보를 찾을 수 없습니다.");
          return;
        }

        const data = await res.json();
        addressInput.value = data.address || "";
        emailInput.value = data.email || "";
        phoneInput.value = data.phone_number || "";
      } catch (err) {
        console.error("조회 중 오류:", err);
        alert("회원 정보 조회 중 오류가 발생했습니다.");
      }
    });

    form.addEventListener("submit", async (e) => {
      e.preventDefault();
      const id = memberIdInput.value;

      const payload = {
        address: addressInput.value,
        email: emailInput.value,
        phone_number: phoneInput.value
      };

      try {
        const res = await fetch(`/api/customerstest/${id}`, {
          method: "PATCH",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(payload)
        });

        if (res.ok) {
          alert("회원 정보가 성공적으로 수정되었습니다.");
        } else {
          alert("회원 정보 수정 실패. 서버 오류 또는 잘못된 입력.");
        }
      } catch (err) {
        console.error("수정 중 오류:", err);
        alert("회원 정보 수정 중 오류가 발생했습니다.");
      }
    });
  });
</script>

<main class="edit-member-container">
  <h1>회원 정보 수정</h1>
  <form class="edit-member-form" bind:this={form}>
    <div class="form-group">
      <label for="customerId">회원 ID</label>
      <input type="text" id="customerId" name="customerId" placeholder="회원 ID" required bind:this={memberIdInput} />
    </div>

    <div class="form-group">
      <label for="address">주소</label>
      <input type="text" id="address" name="address" placeholder="새로운 주소 입력" bind:this={addressInput} />
    </div>

    <div class="form-group">
      <label for="email">이메일</label>
      <input type="email" id="email" name="email" placeholder="새로운 이메일 입력" bind:this={emailInput} />
    </div>

    <div class="form-group">
      <label for="phone_number">전화번호</label>
      <input type="tel" id="phone_number" name="phone_number" placeholder="새로운 전화번호 입력" bind:this={phoneInput} />
    </div>

    <button type="submit" class="edit-btn">정보 수정</button>
  </form>
</main>

<style>
  .edit-member-container {
    max-width: 600px;
    margin: 40px auto;
    padding: 30px;
    background-color: #fff;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
  }

  h1 {
    text-align: center;
    font-size: 2rem;
    margin-bottom: 30px;
    color: #2c3e50;
  }

  .edit-member-form {
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
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 8px;
    outline: none;
  }

  input:focus {
    border-color: #4a90e2;
  }

  .edit-btn {
    background-color: #2c3e50;
    color: white;
    border: none;
    padding: 14px;
    border-radius: 8px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  .edit-btn:hover {
    background-color: #1a2a3a;
  }
</style>
