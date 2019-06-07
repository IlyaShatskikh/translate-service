<template>
  <div>
    <b-navbar type="light" variant="light">
      <b-navbar-brand class="text-monospace" id="brand" :to="{ name: 'Home' }">translate service</b-navbar-brand>
      <b-navbar-nav>
        <b-nav-item :to="{ name: 'Translations' }">Translate</b-nav-item>
      </b-navbar-nav>
      <b-navbar-nav class="ml-auto">
        <b-nav-item-dropdown v-if="isLogged()" v-bind:text="getUsername" right>
          <b-dropdown-item >Profile</b-dropdown-item>
          <b-dropdown-item :to="{ name: 'Logout' }">Log Out</b-dropdown-item>
        </b-nav-item-dropdown>
        <b-nav-item v-else :to="{ name: 'Login' }">Log in</b-nav-item>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
import { authComputed } from '../state/helpers';

export default {
  name: 'Navbar',
  computed: {
    ...authComputed,
  },
};
</script>

<style>

</style>
