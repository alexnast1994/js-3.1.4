async function getAllTodos() {
    const res = await fetch('http://localhost:8080/main');
    const main = await res.json();

    console.log(main);
}

window.addEventListener('DOMContentLoaded', getAllTodos);
