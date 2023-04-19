<template>
  <div class="items">
    <img
      class="image1"
      src="../assets/pexels-tirachard-kumtanom-733852.jpg"
      alt="background image"
    />
    <div class="item-lists">
      <h1 class="items-list-title">Items for this list</h1>
      <router-link
        tag="button"
        class="open-create-form"
        :to="{ name: 'AddItem', params: { listId: currentList } }"
        >Add a item to this list</router-link
      >
      <item-list class="component" v-bind:listId="currentList"></item-list>
    </div>
  </div>
</template>

<script>
import ItemList from "../components/ItemList.vue";
export default {
  components: { ItemList },
  computed: {
    currentList() {
      return this.$route.params.listId;
    },
  },
};
</script>

<style scoped>
.items-list-title {
  font-size: 32px;
}
.image1 {
  min-height: 100%;
  min-width: 1024px;
  width: 100%;
  height: auto;
  position: fixed;
  top: 0px;
  left: 0px;
  z-index: -1;
}
.items-list {
  align-items: center;
  background: linear-gradient(90deg, white, white);
  width: 760px;
  padding: 30px;
  margin: auto;
  text-align: center;
  padding-top: 120px;
  box-shadow: 0px 0px 10px black;
  /* z-index: 1; I have added z-index and height from original .screen__content, other staff is from .screen from original  */
  /**/
  height: auto;
}
.open-create-form {
  justify-content: center;
  margin: 0 auto;
  width: 50%;
  background: #fff;
  font-size: 14px;
  margin-top: 30px;
  padding: 16px 20px;
  /* border-radius: 26px; */
  border: 1px solid #d4d3e8;
  text-transform: uppercase;
  text-align: justify;
  font-weight: 700;
  display: flex;
  align-items: center;
  width: 40%;
  color: black;
  box-shadow: 0px 2px 2px black;
  cursor: pointer;
  transition: 0.2s;
}
.component {
  display: grid;
}
</style>