<template>
  <v-app-bar color="primary" dense dark app>
    <v-toolbar-title>
      <router-link :to="{ name: 'Home' }" tag="div" class="titulo">
        Minhas Tarefas
      </router-link>
    </v-toolbar-title>

    <v-spacer></v-spacer>

    <v-menu left bottom>
      <template v-slot:activator="{ on, attrs }">
        <v-btn icon v-bind="attrs" v-on="on">
          <v-icon>mdi-cogs</v-icon>
        </v-btn>
      </template>

      <v-list>
        <v-list-item :to="{ name: 'Categoria' }">
          <v-list-item-title>Categoria</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

    <v-btn text @click="logout" v-if="usuario" >Sair</v-btn>
  </v-app-bar>
</template>

<script>
export default {
  computed: {
    usuario() {
      return this.$store.getters.usuario
    }
  },
  methods: {
    logout() {
      this.$store.dispatch('logout')
    }
  }
};
</script>

<style>
.titulo:hover {
  cursor: pointer;
}
</style>