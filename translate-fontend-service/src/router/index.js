import Vue from 'vue';
import Router from 'vue-router';
import store from '@/state/store';
import Home from '@/components/Home.vue';
import Login from '@/components/Login.vue';
import Translations from '@/components/Translations.vue';

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home,
    },
    {
      path: '/translations',
      name: 'Translations',
      component: Translations,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {
        beforeResolve(routeTo, routeFrom, next) {
          if (store.getters['auth/isLogged']) {
            next({ name: 'Home' });
          } else {
            next();
          }
        },
      },
    },
    {
      path: '/logout',
      name: 'Logout',
      meta: {
        requiresAuth: true,
      },
      beforeEnter(routeTo, routeFrom, next) {
        store.dispatch('auth/logout');
        next({ name: 'Home' });
      },
    },
  ],
});

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!store.getters['auth/isLogged']()) {
      next({
        path: '/login',
        query: { redirect: to.fullPath },
      });
    } else {
      next();
    }
  } else {
    next();
  }
});

export default router;
