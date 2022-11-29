import { createWebHistory, createRouter } from "vue-router"

const routes = [
    { path : '/', name: "home", 
        component : () => import("@/views/Home.vue") 
    },
    { path : '/families', name: "fam",
      component : () => import("@/views/families/Index.vue") 
    },
    { path : '/families/create', name : "fam-create", 
      component : () => import("@/views/families/Create.vue") 
    },
    {
      path: '/families/edit/:id', name: "fam-edit",
      component: () => import("@/views/families/Edit.vue")
    },
    {
      path: '/flowers', name: "flo",
      component: () => import("@/views/flowers/Index.vue")
    },
    {
      path: '/flowers/create', name: "flo-create",
      component: () => import("@/views/flowers/Create.vue")
    },
    {
      path: '/flowers/edit/:id', name: "flo-edit",
      component: () => import("@/views/flowers/Edit.vue")
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;