<template>
  <div class="main-div">
    <img
      class="image1"
      src="../assets/pexels-tirachard-kumtanom-733852.jpg"
      alt="background image"
    />
    <div class="list">
      <h1 class="list-title">Title: {{ currentGroup.groupName }}</h1>
      <list-of-lists v-bind:groupId="currentGroup.groupId"></list-of-lists>
      <router-link
        tag="button"
        class="add-list-button"
        :to="{
          name: 'AddList',
          params: { groupId: currentGroup.groupId },
        }"
        ><i class="fa-solid fa-plus"></i
      ></router-link>
    </div>
  </div>
</template>

<script>
import ListOfLists from "../components/ListOfLists.vue";

export default {
  components: {
    ListOfLists,
  },
  computed: {
    currentGroup() {
      //   return this.$route.params.groupId;
      // },
      return this.$store.state.groups.find(
        (group) => group.groupId === this.$route.params.groupId
      );
    },
  },
};
</script>
<style scoped>
.main-div {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
}
.list-title {
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
.list {
  align-items: center;
  background: rgba(255, 255, 255, 0.911);
  width: 760px;
  padding: 30px;
  margin: auto;
  text-align: center;
  padding-top: 120px;
  box-shadow: 0px 0px 10px black;
  /* height: 600px; */
}
.add-list-button {
  background: #fff;
  border: 1px solid #d4d3e8;
  color: black;
  justify-content: center;
  margin: 0 auto;
  width: 100px;
  height: 100px;
  border-radius: 50%;
  padding: 20px;
  text-align: center;
  display: inline-block;
  font-size: 34px;
  margin-top: 30px;

  font-weight: 700;
  display: flex;
  align-items: center;
  text-transform: uppercase;

  box-shadow: 0px 2px 2px black;
  cursor: pointer;
  transition: 0.2s;
}
</style>
