<template>
  <div id="app">
    <Header />
    <router-view />
  </div>
</template>

<script>
import Header from "../components/layout/Header.vue";

export default {
  name: "MainLayout",
  components: {
    Header
  }
};
</script>

<style></style>
