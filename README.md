MeliApp - Aplicativo de Busca de Produtos do Mercado Livre

Este é um aplicativo Android desenvolvido em Kotlin utilizando Jetpack Compose e arquitetura MVVM + Clean Architecture, que permite ao usuário buscar produtos no Mercado Livre, visualizar detalhes e imagens, e navegar de forma fluida com suporte a rotação de tela e tratamento de erros.

⸻

:rocket: Funcionalidades
	•	Tela de Busca de produtos
	•	Tela de Resultados com lista de produtos encontrados
	•	Tela de Detalhes com:
	•	Carousel de imagens com indicador (ViewPager2 + WormDotsIndicator)
	•	Preço formatado com moeda brasileira
	•	Categoria
	•	Descrição expansível (“Ver mais” / “Ver menos”)
	•	Splash screen personalizada com logo do Mercado Livre
	•	Integração com API do Mercado Livre (mockado/local ou futura integração via OAuth2.0)
	•	Navegação com Navigation Component
	•	Suporte à rotação de tela
	•	Tratamento de erros de busca e exibição de mensagens amigáveis

⸻

:hammer: Tecnologias Utilizadas
	•	Kotlin (100%)
	•	Jetpack Compose para UI (se aplicável)
	•	XML Layouts (MotionLayout, ViewPager2)
	•	Navigation Component
	•	ViewModel + StateFlow
	•	Koin para injeção de dependência
	•	ViewBinding
	•	Coroutines + Flow
	•	Material Design 3

⸻

:iphone: Telas do App
	•	SplashScreen com logo
	•	Tela de Busca com EditText + Botão
	•	Lista de resultados com:
	•	Imagem (ImageView)
	•	Título e preço
	•	Divider entre itens
	•	Ícone chevron indicando navegabilidade
	•	Tela de Detalhes:
	•	Carousel de imagens com animação
	•	Título, Preço, Categoria
	•	Texto expansível com “Ver mais” / “Ver menos”

⸻

:lock: Gerenciamento de Sessão
	•	SessionManager com SharedPreferences
	•	Confirmação de logout com AlertDialog
	•	Redirecionamento para Login se aplicável

⸻

:floppy_disk: Estrutura de Diretórios

com.meli
├── di/                 <- Módulos do Koin
├── data/              <- Camada de dados e mocks
├── domain/            <- Camada de usecases e models
├── presentation/
│   ├── search/        <- Tela de busca
│   ├── results/       <- Tela de resultados
│   ├── detail/        <- Tela de detalhes
│   └── components/    <- Adapters e componentes visuais reutilizáveis
├── util/              <- Extensões, helpers e formatações
├── MainActivity.kt


⸻

:bookmark: Requisitos
	•	Android Studio Giraffe ou superior
	•	Android SDK 31+
	•	Gradle 8.2+

⸻

Feito para fins de aprendizado e demonstração de habilidades Android.
