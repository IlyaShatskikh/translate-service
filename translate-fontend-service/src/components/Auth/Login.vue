<template>
      <div id="table" class="container">
        <b-container class="col-lg-3 border rounded p-3 shadow">
          <b-row>
            <b-col >
              <b-form>
                  <b-form-group>
                      <b-form-input v-model="username" type="text" placeholder="Enter username" required/>
                  </b-form-group>

                  <b-form-group>
                      <b-form-input v-model="password" type="password" placeholder="Enter password" required/>
                  </b-form-group>

                  <b-button block class="text-monospace" type="submit" variant="primary" @click.prevent="login" >Sing In</b-button>
                  <b-button block class="text-monospace" type="button" variant="link" @click="$emit('swap-form')">Sing Up</b-button>
              </b-form>
            </b-col>
          </b-row>
        </b-container>
        <b-container class="col-lg-7 p-3">
          <b-alert v-model="showErrorAlert" variant="danger" dismissible v-on:dismissed="cleanError()">
            <li v-bind:key="idx" v-for="(error, idx) of errors">
              {{error}}
            </li>
          </b-alert>
        </b-container>
      </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      username: '',
      password: '',
      showErrorAlert: false,
      errors: [],
    };
  },

  methods: {
    login() {
      this.cleanError();

      const { username, password } = this;
      if (username.length < 1) {
        this.errors.push('Username is empty');
      }

      if (password.length < 1) {
        this.errors.push('Password is empty');
      }

      if (this.errors.length) {
        this.showErrorAlert = true;
        return null;
      }

      this.$store
        .dispatch('auth/login', { username, password })
        .then((e) => {
          if (e) {
            this.errors.push(e);
            this.showErrorAlert = true;
            return null;
          }
          return this.$router.push('/');
        });
      return null;
    },
  },
};

</script>

<style>

</style>
