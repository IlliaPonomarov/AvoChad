<template>
  <div class="my-button">
    <q-btn color="black" >{{ buttonLabel }}</q-btn>
  </div>
</template>

<script>
export default {
  name: "MyButton",
  props: {
    buttonLabel: String
  }

}
</script>

<style scoped>

</style>
