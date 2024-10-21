# Fishbotica - Extension FastScan

## Membres du Groupe
- Membre 1: [Benault Alexandre]
- Membre 2: [Masquelier Orféo]
- Membre 3: [Da Cruz Théo]

## Sujet du Projet
  Développement d'une application pour simplifier les tests chimiques d'un aquarium

## Description du Projet
Une solution innovante qui simplifie la gestion des aquariums grâce à des conseils personnalisés et à une analyse en temps réel de l'écosystème. Fini les erreurs fréquentes ! Avec cette technologie, vos poissons évoluent dans un environnement optimisé pour leur bien-être. Notre mission ? Proposer des outils éducatifs et une expérience interactive pour sensibiliser à l’aquariophilie💡. L'extension FastScan a pour objectif de faciliter le suivi des paramètres de l'eau de votre aquarium en scannant des bandelettes de test. Elle permet d'obtenir rapidement et simplement les valeurs mesurées, telles que le pH, le taux de nitrates ou d'autres paramètres clés. Grâce à cette extension, vous pouvez non seulement accéder aux résultats de vos analyses en quelques secondes, mais également bénéficier d'un historique complet pour un suivi régulier et précis de la qualité de l'eau de votre aquarium.

## Outils utilisés
- Supabase pour la gestion de l'authentification et de la base de données
- API Flask Python pour recevoir les données de mon eau à partir de l'image d'une bandelette, deployé via Render

## Difficultés rencontrées
- Gestion des ViewModel
- Authentification via SUPABASE
- POST d'une image sur l'API Python (test réalisé via Postman)

## Lancement du projet 
Pour lancer le projet, il faut ajouter les deux parametres suivants dans local.properties : 
SUPABASE_API_KEY = eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJuenVvdnRvdmViandoY2F2b2xiIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mjc4NTQ3MDEsImV4cCI6MjA0MzQzMDcwMX0.AW5uL_np0cpkMqFdZ2zVgT19X4qS4dTR8Rb2NQvH814
SUPABASE_URL = https://rnzuovtovebjwhcavolb.supabase.co

De plus, si vous souhaitez tester l'API python, il faut nous demander pour qu'on la deploie.

