<template>
  <div id="table" class="container">
    <ul v-if="errors && errors.length">
      some connection problems
      <li v-bind:key="error" v-for="error of errors">
        {{error.message}}
      </li>
    </ul>
    <div v-else>
      <b-form-input class="mb-2 w-25 float-right" id="input-filter" v-model="filter" placeholder="Enter text"></b-form-input>
      <b-table hover :items="translations" :fields="fields" :filter="filter">
        <template slot="action" slot-scope="row">
          <b-button size="sm" @click="deleteTranslation(row)" class="mr-2 btn-danger">
            Delete
          </b-button>
        </template>
      </b-table>
    </div>
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
    };
  },
  mounted() {
    this.getAllTranslations();
  },
  methods: {
    getAllTranslations() {
      axios
        .get('http://localhost:8081/translations')
        .then((response) => {
          this.translations = response.data;
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },
    deleteTranslation(row) {
      const translation = row.item;
      axios
        .delete(`http://localhost:8081/translations/${translation.id}`)
        .then(() => {

        })
        .catch(() => {

        });
    },
  },
};
</script>

<style scoped>
  ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
  }

  li {
    font: 200 20px/1.2 Courier, Courier, sans-serif;
    border-bottom: 1px solid #ccc;
  }
</style>
