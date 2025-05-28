# 🛒 MeliApp - Aplicativo de Busca de Produtos do Mercado Livre

Aplicativo Android desenvolvido em **Kotlin** com **arquitetura MVVM + Clean Architecture**, que permite ao usuário buscar produtos no Mercado Livre, visualizar resultados e detalhes com imagens, descrição expansível e muito mais.

---

## 🚀 Funcionalidades

- 🔍 Tela de **Busca** de produtos  
- 📋 Tela de **Resultados** com:
  - Imagem do produto
  - Título e preço formatado (R$)
  - Divider entre itens
  - Ícone com setinha (chevron) para indicar navegabilidade  
- 📄 Tela de **Detalhes** com:
  - **Carousel de imagens** (ViewPager2 + WormDotsIndicator)
  - Preço formatado com moeda brasileira
  - Categoria do produto
  - **Descrição expansível** com botão “Ver mais” / “Ver menos”
- 🟡 **Splash screen personalizada** com logo do Mercado Livre
- 🔄 **Navegação** entre telas com Navigation Component
- 🧭 Suporte completo à **rotação de tela**
- 🛑 Tratamento de **erros de busca** e mensagens amigáveis

---

## 🔨 Tecnologias Utilizadas

- ✅ **Kotlin** 100%
- ✅ **ViewModel + StateFlow**
- ✅ **Koin** (injeção de dependência)
- ✅ **Coroutines + Flow**
- ✅ **ViewBinding**
- ✅ **MotionLayout** e **ViewPager2**
- ✅ **WormDotsIndicator**
- ✅ **Material Design 3**
- ✅ **Extensões (Extensions.kt)** para formatação de preço, links HTTPS e texto expansível

---

## 🧪 Testes

- ✅ Testes **unitários** com `kotlinx-coroutines-test` e `Turbine`
- ✅ Testes **instrumentados (UI)** com `FragmentScenario`, `Espresso` e `KoinTestRule`

### Exemplos:
- `SearchViewModelTest`: valida mudanças de estado com queries
- `SearchFragmentTest`: valida interações de UI (digitação, clique e navegação)

---

## 📱 Telas do App

- 🟡 SplashScreen com logo
- 🔍 Tela de Busca (campo de texto e botão)
- 📋 Lista de resultados
- 📄 Detalhes com:
  - Carousel animado
  - Indicador de página
  - Título, preço, categoria
  - Texto expansível

---

## 🔒 Gerenciamento de Sessão

- SessionManager com `SharedPreferences`
- Confirmação de logout com `AlertDialog`
- Redirecionamento automático para a tela de Login (modo `!IS_MOCK`)

---

## 🛠 Requisitos

- Android Studio **Giraffe** ou superior  
- SDK mínimo: **26+**  
- Gradle: **8.2+**  
- Kotlin: **1.9+**

---

## 🎯 Observações

> O app está preparado para receber autenticação OAuth 2.0 futuramente e já possui estrutura de `SessionManager` e `BuildConfig.IS_MOCK`.

---
