<template>
  <div id="table" class="container">
      <b-form-input class="mb-2 w-25 float-right" id="input-filter" v-model="filter"></b-form-input>
    <b-table hover :items="translations" :filter="filter"></b-table>
  </div>
</template>

<script>
export default {
  name: 'Translations',
  components: {
  },
  props: [],
  data() {
    return {
      translations: [
        {
          id: 0,
          lang_from: 'ru',
          lang_to: 'en',
          text_orig: 'Привет, Мир0!',
          text_result: 'Hello, World0!',
        },
        {
          id: 1,
          lang_from: 'ru',
          lang_to: 'en',
          text_orig: 'Привет, Мир1!',
          text_result: 'Hello, World1!',
        },
        {
          id: 2,
          lang_from: 'ru',
          lang_to: 'en',
          text_orig: 'Привет, Мир2!',
          text_result: 'Hello, World2!',
        },
      ],
      filter: null,
    };
  },
};
</script>

<style>
</style>
