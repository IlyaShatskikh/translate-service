<template>
  <div id="table" class="container">
    <b-container class="col-lg-3 border rounded p-3 shadow">
      <b-row class>
        <b-col>
          <b-form>
            <b-form-group>
              <b-form-input v-model="username" type="text" placeholder="Enter username" required autofocus/>
            </b-form-group>

            <b-form-group>
              <b-form-input v-model="password" type="password" placeholder="Enter password" required/>
            </b-form-group>

            <template v-if="!operation.login">
              <b-form-group>
                <b-form-input v-model="passwordConfirm" type="password" placeholder="Confirm password" required/>
              </b-form-group>

              <b-form-group>
                <b-form-input v-model="email" type="email" placeholder="Enter email" required/>
              </b-form-group>
            </template>

            <b-button block class="text-monospace" type="submit" variant="primary">{{operation.getText()}}</b-button>
            <b-button block class="text-monospace" type="button" variant="link" @click="operation.switchOperation()">{{operation.getAnotherText()}}</b-button>
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
      passwordConfirm: '',
      email: '',

      operation: {
        values: { true: 'Sing in', false: 'Sing up' },
        login: true,

        getText() {
          return this.values[this.login];
        },

        getAnotherText() {
          return this.values[!this.login];
        },

        switchOperation() {
          this.login = !this.login;
        },
      },

      showErrorAlert: true,
      errors: [],
    };
  },
  methods: {
    cleanError() {
      this.errors = [];
    },
    register({ username, password, passwordConfirm, email }) {
      if (password !== passwordConfirm) {
        this.errors.push('Password and confirmation password do not match');
        this.showErrorAlert = true;
        return null;
      }
      return null;
    },
    login({ username, password }) {
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
