<template>
  <div class="create">
    <img
      class="image1"
      src="../assets/pexels-tirachard-kumtanom-733852.jpg"
      alt="background image"
    />

    <!-- <h1>Create new list</h1> -->
    <create-list class="component" v-bind:groupId="currentGroup" />
  </div>
</template>

<script>
import CreateList from "../components/CreateList.vue";
export default {
  components: { CreateList },
  computed: {
    currentGroup() {
      return this.$route.params.groupId;
    },
  },
};
</script>

<style scoped>
.image1 {
  min-height: 100%;
  min-width: 1024px;
  width: 100%;
  height: auto;
  position: fixed;
  top: 0px;
  left: 0px;
  z-index: -3;
}
.create-form {
  background: linear-gradient(90deg, white, white);
  position: relative;
  /* top: 0px;
  right: 0px;
  bottom: 0px;
  left: 0px; */
  width: 760px;
  padding: 30px;
  padding-top: 120px;
  box-shadow: 0px 0px 10px #5c5696;
  /* z-index: 1; I have added z-index and height from original .screen__content, other staff is from .screen from original  */
  height: 100%;
  /**/
  height: 600px;
  /* box-shadow: 0px 0px 24px #5c5696; */
}
.create {
  padding-top: 100px;
  text-align: center;
  margin: auto;
  display: flex;
  justify-content: center;
  align-content: center;
}
</style>