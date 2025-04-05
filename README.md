🌾 Khet Guru
Khet Guru is a smart agriculture assistant app designed to empower Indian farmers by integrating AI, ML, and real-time data. It helps farmers make informed decisions by providing personalized crop suggestions, disease detection, weather forecasts, market insights, and expert guidance.

📱 Features
🧠 AI & ML Integration
Crop Suitability Analysis: Recommends crops based on soil data using a Random Forest Classifier.

Disease Detection: Identifies crop diseases and suggests biological/chemical treatments.

Demand Forecasting: AI-driven analysis of market trends and demand.

🌦️ Weather Forecasting
Real-time weather updates using a weather API for better farm planning.

Temperature, rainfall prediction, and alerts for extreme conditions.

🧪 Soil Health Monitoring
Suggests suitable crops and fertilizers based on soil input parameters.

🐛 Pest & Disease Management
Detects pest issues and recommends appropriate eco-friendly and chemical solutions.

🛒 Market Price & Demand Updates
Integrates data.gov.in APIs for:

Daily mandi commodity prices.

Crop demand trends and region-wise market insights.

👨‍🌾 Expert Advisory (Chatbot)
AI-powered chatbot for providing tailored farming advice and answering queries.

🏞️ Government Schemes
Up-to-date information on schemes, subsidies, and loans for farmers.

🔐 User Authentication
Firebase Authentication: Secure login and registration for farmers.

🏗️ Tech Stack
Layer	Technology
Frontend	Jetpack Compose (Kotlin)
Backend	Firebase Firestore (for online database)
Authentication	Firebase Authentication
AI/ML	Scikit-learn (Random Forest Classifier), Python
APIs	
data.gov.in (Market prices, demand)
WeatherAPI (for weather forecasting) 

🧪 ML Model Overview
Model: Random Forest Classifier

Use Case: Predict optimal crops based on soil parameters

Built with: Python, Pandas, Scikit-learn

🚀 Future Enhancements
Voice assistant for multilingual and illiterate user support

Offline functionality with local storage

TensorFlow Lite for on-device disease prediction

Regional language support
