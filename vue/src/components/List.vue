<template>
  <div class="list">
    <h1 class="list-names">{{ list.listName }}</h1>
    <h2>quantity : {{ list.numOfItems }}</h2>
  </div>
</template>

<script>
export default {
  name: "list-card",
  props: ["list"],
};
</script>

<style scoped>
.list {
  border: 20px;
}
</style>