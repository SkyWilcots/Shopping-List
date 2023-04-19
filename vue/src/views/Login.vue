<template>
  <div id="login" class="text-center">
    <div class="parent">
      <img
        class="image1"
        src="../assets/pexels-tirachard-kumtanom-733852.jpg"
        alt="background image"
      />
      <img class="image2" src="../assets/monika's.png" alt="logo" />
    </div>
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Welcome to Monika's List</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>

      <label for="username" class="sr-only"></label>
      <div class="login__field">
        <link
          rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Raleway:400,700"
        />
        <i class="fa fa-user" aria-hidden="true"></i>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Email"
          v-model="user.username"
          required
          autofocus
        />
      </div>

      <label for="password" class="sr-only"></label>
      <div class="login__field">
        <i class="fa-solid fa-lock"></i>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
      </div>

      <div class="buttons">
        <button type="submit" class="sign-in-and-registration-buttons">
          Sign in
        </button>

        <router-link
          tag="button"
          class="sign-in-and-registration-buttons"
          :to="{ name: 'register' }"
          >Create an account</router-link
        >
      </div>
    </form>
  </div>
</template>


<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
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
body #login {
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

.form-signin {
  background: rgba(255, 255, 255, 0.911);
  position: absolute;
  width: 760px;
  padding: 30px;
  margin: auto;
  text-align: center;
  padding-top: 120px;
  box-shadow: 0px 0px 10px black;
  height: 100%;
  height: 600px;
}

.login__field {
  padding: 20px 0px;
  position: relative;
}
.buttons {
  margin: 0 auto;
  width: 50%;
}
.sign-in-and-registration-buttons {
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

.sign-in-and-registration-buttons:active,
.sign-in-and-registration-buttons:focus,
.sign-in-and-registration-buttons:hover {
  border-color: #6a679e;
  outline: none;
}
.login {
  width: 320px;
  padding: 30px;
  padding-top: 156px;
}
.login__field {
  padding: 20px 0px;
  position: relative;
}

.login__icon {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 30px;
  color: white;
  z-index: 1;
}
</style>