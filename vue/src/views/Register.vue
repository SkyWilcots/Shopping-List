<template>
  <div id="register" class="text-center">
    <div class="parent">
      <img
        class="image1"
        src="../assets/pexels-tirachard-kumtanom-733852.jpg"
        alt="background image"
      />
      <img class="image2" src="../assets/monika's.png" alt="logo" />
    </div>
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">create account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="login__field">
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Type Email"
          v-model="user.username"
          required
          autofocus
        />
      </div>

      <div class="login__field">
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Type Password"
          v-model="user.password"
          required
        />
      </div>
      <div class="login__field">
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
      </div>

      <div class="registration-buttons">
        <button class="create-and-have-an-account-buttons" type="submit">
          Submit
        </button>
        <router-link
          tag="button"
          class="create-and-have-an-account-buttons"
          :to="{ name: 'login' }"
          >Already have an account?</router-link
        >
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scope>
@import url("https://fonts.googleapis.com/css?family=Raleway:400,700");

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  font-family: Raleway, sans-serif;
}
body #register {
  background: linear-gradient(90deg, white, rgb(2, 2, 107));
}
.image1 {
  min-height: 100%;
  min-width: 1024px;
  width: 100%;
  height: auto;
  position: fixed;
  top: 0px;
  left: 0px;
}

.image2 {
  position: fixed;
  bottom: 0px;
  left: 0px;
  text-align: center;
}
.text-center {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
}
.form-register {
  background: rgba(255, 255, 255, 0.911);
  position: absolute;
  width: 760px;
  padding: 70px;
  margin: auto;
  text-align: center;
  box-shadow: 0px 0px 10px #5c5696;
  height: 100%;
  height: 600px;
}
h1 {
  color: black;
  text-align: center;
  font-size: 28px;
}
.form-control {
  border: none;
  border-bottom: 2px solid #d1d1d4;
  background: none;
  padding: 10px;
  padding-left: 24px;
  font-weight: 700;
  width: 75%;
  transition: 0.2s;
}

.form-control:active,
.form-control:focus,
.form-control:hover {
  outline: none;
  border-bottom-color: #6a679e;
}

.login__field {
  padding: 20px 0px;
  position: relative;
  text-align: center;
}

.form-control:active,
.form-control:focus,
.form-control:hover {
  outline: none;
  border-bottom-color: #6a679e;
}

.create-and-have-an-account-buttons {
  justify-content: center;
  background: #fff;
  font-size: 14px;
  margin-top: 30px;
  padding: 16px 20px;
  border: 1px solid #d4d3e8;
  text-transform: uppercase;
  font-weight: 700;
  display: flex;
  align-items: center;
  width: 100%;
  color: black;
  box-shadow: 0px 2px 2px black;
  cursor: pointer;
  transition: 0.2s;
}
.registration-buttons {
  margin: 0 auto;
  width: 50%;
}

.create-and-have-an-account-buttons:active,
.create-and-have-an-account-buttons:focus,
.create-and-have-an-account-buttons:hover {
  border-color: #6a679e;
  outline: none;
}
</style>
