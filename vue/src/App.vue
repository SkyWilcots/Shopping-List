<template>
  <div id="app">
    <div id="nav" class="navigation">
      <router-link v-bind:to="{ name: 'home' }"
        ><i class="fa fa-home" aria-hidden="true"></i></router-link
      >&nbsp;|

      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        ><i class="fa fa-sign-out" aria-hidden="true"></i>
      </router-link>
    </div>
    <router-view />
  </div>
</template>
<style scoped>
.navigation {
  text-decoration: none;
}
</style>
