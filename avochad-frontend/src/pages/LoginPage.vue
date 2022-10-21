<template>
  <q-page>
    <div class="column q-pa-lg window-height window-width row justify-center items-center">
      <div class="row">
        <div class="text-white text-h5 justify-start items-start text-bold q-mb-lg">Sing In</div>
      </div>
      <div class="row">
        <q-card square class="shadow-24">
          <q-form class="q-gutter-lg-md" @submit.prevent.stop="onSubmit()">
          <q-card-section class="q-pa-lg-lg">
              <q-input
                       :rules="emailValidate"
                       class="q-ma-lg-md"
                       v-model="loginFormState.email"
                       ref="emailRef"
                       standout
                       type="text"
                       hint="Enter your email"
                       lazy-rules
                       label="Email / Nickname">

                <template v-slot:prepend>
                  <q-icon name="email" />
                </template>
              </q-input>
              <br/>
              <q-input
                :rules="passwordValidate"
                class="q-ma-lg-md"
                v-model="loginFormState.password"
                ref="passwordRef"
                type="password"
                standout
                lazy-rules
                label="Password">

                <template v-slot:prepend>
                  <q-icon name="lock" />
                </template>
                <template v-slot:append>
                  <q-icon class="cursor-pointer" />
                </template>
              </q-input>

          </q-card-section>

          <q-card-actions class="q-px-lg">
            <MyButton btn-type="submit" btn-label="sing in"></MyButton>
            <!--   I wait while you push your registration page, motherfucker         -->
            <OutlineButtons btn-type="button" @click="$router.replace('/')" class="q-ml-lg" btn-label="sing up"></OutlineButtons>
          </q-card-actions>
          </q-form>
            <div class="text-center q-ml-lg q-mb-lg row">
              <!--   Don't forgot about it           -->
              <a href="#" class="text-grey-6">Forgot password?</a>
            </div>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script lang="ts">
import MyButton from "../components/MyButton.vue"
import Validator from '../validation/Validator'
import { useQuasar } from "quasar"
import { computed, reactive } from "vue"
import OutlineButtons from "components/OutlineButtons.vue"
import { mapActions, mapGetters, useStore } from "vuex"
import useMainStore from "src/store/chatStore"
import router from "src/router"
import { useRoute, useRouter } from "vue-router"

export default {
  name: "LoginPage",
  components: { OutlineButtons, MyButton },

  setup () {
    const emailValidate = [(val: string) => Validator.emailValidation(val) || Validator.nicknameValidation(val) || 'Enter email correctly']
    const passwordValidate = [(val: string) => Validator.passwordValidation(val) || 'Enter password correctly']
    const store = useMainStore()
    const router = useRouter()
    const route = useRoute()
    type AccountFormState = {
      email: string;
      password: string;
    };

    const loginFormState: AccountFormState = reactive({
      email: '',
      password: ''
    })

    function onSubmit (): void {
      const emailL = loginFormState.email
      const pass = loginFormState.password

      store.authorizedUser = {
        email: emailL,
        password: pass
      }

      router.push({
        path: '/chats'
      })
    }

    return {
      loginFormState, onSubmit, emailValidate, passwordValidate
    }
  }

}
</script>

<style scoped>

</style>
