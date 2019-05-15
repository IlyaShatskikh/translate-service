<template>
    <b-container id="app">
      <b-row class="mb-3">
        <b-col>
          <navbar/>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <router-view/>
        </b-col>
      </b-row>
    </b-container>
</template>

<script>
import Navbar from '@/components/Navbar.vue';

export default {
  name: 'App',
  components: {
    Navbar,
  },
};
</script>


<style>

</style>
