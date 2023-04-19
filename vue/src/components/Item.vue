<template>
  <div class="items">
    <h1 class="item-names">{{ item.itemName }}</h1>
    <h2 class="items-on-list">Quantity : {{ item.quantity }}</h2>
    <button
      class="btnDeleteItem"
      @click.prevent="deleteItem(item.listId, item.itemId)"
    >
      Delete
    </button>
  </div>
</template>

<script>
import ItemService from "../services/ItemService.js";

export default {
  name: "item-card",
  props: ["item"],
  methods: {
    deleteItem(listId, itemId) {
      ItemService.delete(listId, itemId).then((response) => {
        if (response.status == 204) {
          this.$store.commit("DELETE_ITEM", itemId);
          this.itemLists();
        }
      });
    },
  },
};
</script>

<style scoped>
.items {
  justify-content: center;
  align-items: center;
}
</style>