<template>
  <div>
    <b-navbar type="dark" variant="dark">
      <b-navbar-brand class="text-monospace" id="brand" :to="{ name: 'Home' }">translate service</b-navbar-brand>
      <b-navbar-nav>
        <b-nav-item href="#">Home</b-nav-item>
        <b-nav-item href="#">Translate</b-nav-item>
        <b-nav-item href="#">Login</b-nav-item>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: 'Navbar',
};
</script>

<style scoped>
b-nav-item a.router-link-active {
    font-weight: 700;
}
</style>
