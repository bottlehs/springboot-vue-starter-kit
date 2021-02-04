<template>
  <div class="error"></div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "404Error",
  components: {
    /**
     * components
     */
  },
  data() {
    /**
     * data
     */
    return {};
  },
  created() {
    /**
     * created
     */
  },
  mounted() {
    /**
     * mounted
     */
  },
  computed: {
    /**
     * computed
     */
    ...mapGetters(["isAuthenticated"])
  },
  destroyed() {
    /**
     * destroyed
     */
  },
  methods: {
    /**
     * methods
     */
  }
};
</script>

<style scoped></style>
