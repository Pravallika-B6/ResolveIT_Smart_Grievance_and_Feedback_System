// Login validation
function validateLogin() {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value;

    if (username === "" || password === "") {
        alert("All fields are required!");
        return false;
    }

    if (password.length < 8) {
        alert("Password must be at least 8 characters long.");
        return false;
    }

    return true;
}

// Signup validation
function validateSignup() {
    const fullname = document.getElementById("fullname").value.trim();
    const email = document.getElementById("email").value.trim();
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value;
    const confirm = document.getElementById("confirm_password").value;

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$/;

    if (!fullname || !email || !username || !password || !confirm) {
        alert("All fields are required!");
        return false;
    }

    if (!emailRegex.test(email)) {
        alert("Enter a valid email address.");
        return false;
    }

    if (!passwordRegex.test(password)) {
        alert("Password must be at least 8 characters, include 1 uppercase, 1 number, and 1 special character.");
        return false;
    }

    if (password !== confirm) {
        alert("Passwords do not match.");
        return false;
    }

    return true;
}
// Attach validation to forms
window.onload = function() {
    document.getElementById("loginForm").onsubmit = validateLogin;
    document.getElementById("signupForm").onsubmit = validateSignup;
};
