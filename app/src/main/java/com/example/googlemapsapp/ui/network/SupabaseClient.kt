package com.example.googlemapsapp.ui.network

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://shithtqbnzjgexpvqyjq.supabase.co",
        supabaseKey = "sb_publishable_IijMwLaGDt-ViyIW-2SGNA_iVQDFNlx"
    ) {
        install(Postgrest)
    }
}