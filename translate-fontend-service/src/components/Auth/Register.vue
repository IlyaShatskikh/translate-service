<template>
  <div id="table" class="container">
    <b-container class="col-lg-3 border rounded p-3 shadow">
      <b-row class>
        <b-col>
          <b-form>
            <b-form-group>
              <b-form-input v-model="username" type="text" placeholder="Enter username" required/>
            </b-form-group>

            <b-form-group>
              <b-form-input v-model="password" type="password" placeholder="Enter password" required/>
            </b-form-group>

            <b-form-group>
              <b-form-input v-model="passwordConfirm" type="password" placeholder="Confirm password" required/>
            </b-form-group>

            <b-form-group>
              <b-form-input v-model="email" type="email" placeholder="Enter email" required/>
            </b-form-group>

            <b-button block class="text-monospace" type="submit" variant="primary" @click.prevent="register">Sing Up</b-button>
            <b-button block class="text-monospace" type="button" variant="link" @click="$emit('swap-form')">Sing In</b-button>
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
  data() {
    return {
      username: '',
      password: '',
      passwordConfirm: '',
      email: '',
      showErrorAlert: true,
      errors: ['Connection lost', 'Username is empty', 'Password is empty'],
    };
  },
  methods: {
    cleanError() {
      this.errors = [];
    },
    register() {
      const {
        username, password, passwordConfirm, email 
      } = this;
      if (password !== passwordConfirm) {
        this.errors.push('Password and confirmation password do not match');
        this.showErrorAlert = true;
        return null;
      }
      return null;
    },
  },
};
</script>
