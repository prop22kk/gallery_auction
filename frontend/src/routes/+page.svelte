<style>
    @import url('https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap');
    :global(body) 
    {
        margin: 0;
        padding: 0;
        height: 100%;
    }

    .centered-wrapper {
        font-family: 'Sunflower', sans-serif;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #1c1f2b;
        text-align: center;
    }

    .logo {
        max-width: 300px;
        width: 80%;
        height: auto;
        margin-bottom: 20px;
    }

    .link {
        font-size: 2.5rem;
        font-family: 'Sunflower', sans-serif;
        color: #cbd6e2;
        text-decoration: none;
    }

    .link:hover {
        color: #555;
    }
</style>

<div class="centered-wrapper">
    <img src="/GA_image.png" alt="Gallery Logo" class="logo" />
    <a href="/customers" class="link">Gallery Auction</a>
</div>
