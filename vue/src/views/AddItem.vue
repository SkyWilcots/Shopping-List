<template>
  <div class="create">
    <img
      class="image1"
      src="../assets/pexels-tirachard-kumtanom-733852.jpg"
      alt="background image"
    />
    <div class="component">
      <!-- <h1>Create new item</h1> -->
      <create-item v-bind:listId="currentList" />
    </div>
  </div>
</template>

<script>
import CreateItem from "../components/CreateItem.vue";
export default {
  components: { CreateItem },
  computed: {
    currentList() {
      return this.$route.params.listId;
    },
  },
};
</script>

<style scoped>
.image1 {
  z-index: -1;
}
.component {
  display: grid;
  place-self: center;
  /* padding-top: 120px;
  padding-left: 500px; */
}
</style>