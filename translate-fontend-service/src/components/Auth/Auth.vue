<template>
    <div>
        <Login v-if="wantLogIn" @swap-form="swapForm" @log-in="$emit('log-in')"/>
        <Register v-else @swap-form="swapForm" @log-in="$emit('log-in')"/>
    </div>
</template>

<script>
import Login from './Login.vue';
import Register from './Register.vue';

export default {
  name: 'Auth',
  data() {
    return {
      wantLogIn: true,
    };
  },
  methods: {
    swapForm() {
      this.wantLogIn = !this.wantLogIn;
    },
    cleanError() {
      this.errors = [];
    },
  },
  components: {
    Login,
    Register,
  },
};
</script>
