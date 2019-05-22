<template>
  <div id="table" class="container">
    <b-container class="mb-3 border rounded p-3 shadow">
        <b-row class="mb-3">
          <b-col >
            <b-form-select id="select-from-language" v-model="selectedFrom" :options="options"></b-form-select>
          </b-col>
          <b-col cols="6" />
          <b-col >
            <b-form-select id="select-from-language" v-model="selectedTo" :options="options"></b-form-select>
          </b-col>
        </b-row>
        <b-row class="mb-3">
          <b-col  >
            <b-form-textarea
              id="textarea-orig"
              v-model="textOrig"
              placeholder="Enter your text..."
              rows="3"
              max-rows="6"
            ></b-form-textarea>
          </b-col>
          <b-col cols="1" md="auto"/>
          <b-col  >
            <b-form-textarea
              id="textarea-result"
              v-model="textResult"
              placeholder="Translated text"
              rows="3"
              max-rows="6"
            ></b-form-textarea>
          </b-col>
        </b-row>
        <b-row>
          <b-col>
            <b-button v-on:click="createTranslation()" class="text-monospace" variant="primary">Translate</b-button>
          </b-col>
        </b-row>
    </b-container>
    <b-container>
      <b-alert v-model="showErrorAlert" variant="danger" dismissible v-on:dismissed="cleanError()">
        <li v-bind:key="error" v-for="error of errors">
          {{error.message}}
        </li>
      </b-alert>
      <b-form-input class="mb-2 w-25 float-right" id="input-filter" v-model="filter" placeholder="Enter text"></b-form-input>
      <b-table hover :items="translations" :fields="fields" :filter="filter">
        <template slot="action" slot-scope="row">
          <b-button size="sm" @click="deleteTranslation(row)" class="mr-2 btn-danger">
            Delete
          </b-button>
        </template>
      </b-table>
    </b-container>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Translations',
  components: {
  },
  props: [],
  data() {
    return {
      fields: {
        origText: {
          label: 'Original text',
          sortable: false,
        },
        lang: {
          label: 'Language',
          sortable: true,
        },
        resultText: {
          label: 'Translated text',
          sortable: false,
        },
        action: {
          label: 'Action',
          sortable: false,
        },
      },
      translations: [],
      filter: null,

      translateResponse: null,
      errors: [],
      showErrorAlert: false,

      selectedFrom: 'ru',
      selectedTo: 'ru',
      options: [
        { value: 'ru', text: 'Russian' },
        { value: 'en', text: 'English' },
        // { value: { C: '3PO' }, text: 'This is an option with object value' },
        // { value: 'd', text: 'This one is disabled', disabled: true }
      ],

      textOrig: null,
      textResult: null,
    };
  },
  mounted() {
    this.getAllTranslations();
  },
  methods: {
    cleanError() {
      this.errors = [];
    },
    getAllTranslations() {
      axios
        .get('http://localhost:8081/translations')
        .then((response) => {
          this.translations = response.data;
        })
        .catch((e) => {
          this.errors.push(e);
          this.showErrorAlert = true;
        });
    },
    deleteTranslation(row) {
      const translation = row.item;
      axios
        .delete(`http://localhost:8081/translations/${translation.id}`)
        .then(() => {
          this.translations.splice(row.index, 1);
        })
        .catch((e) => {
          this.errors.push(e);
          this.showErrorAlert = true;
        });
    },
    createTranslation() {
      const request = {
        origText: this.textOrig,
        lang: `${this.selectedFrom}-${this.selectedTo}`,
      };
      axios
        .post('http://localhost:8081/translations/', request)
        .then(() => {
          this.getAllTranslations();
        })
        .catch((e) => {
          this.errors.push(e);
          this.showErrorAlert = true;
        });
    },
  },
};
</script>

<style scoped>

</style>
