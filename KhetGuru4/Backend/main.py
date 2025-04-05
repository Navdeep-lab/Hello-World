from fastapi import FastAPI
from pydantic import BaseModel
import pandas as pd
import joblib
import os

# Initialize FastAPI app
app = FastAPI()

@app.get("/")
def home():
    return {"message": "Crop Recommendation API is running!"}

# Get the absolute path of the current script
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
MODEL_PATH = os.path.join(BASE_DIR, "soil.ipynb")
ENCODER_PATH = os.path.join(BASE_DIR, "label_encoder.pkl")

# Load Model and Encoder
try:
    model = joblib.load(MODEL_PATH)
    encoder = joblib.load(ENCODER_PATH)
    print("Model and encoder loaded successfully!")
except FileNotFoundError as e:
    raise FileNotFoundError(f"Error: {e}. Ensure the files exist at {MODEL_PATH} and {ENCODER_PATH}")

# Define Request Body Model
class CropInput(BaseModel):
    N: float
    P: float
    K: float
    temperature: float
    humidity: float
    ph: float
    rainfall: float

# Define API Endpoint
@app.post("/predict")
def predict_crop(data: CropInput):
    input_data = pd.DataFrame([[data.N, data.P, data.K, data.temperature, data.humidity, data.ph, data.rainfall]],
                              columns=["N", "P", "K", "temperature", "humidity", "ph", "rainfall"])
    prediction = model.predict(input_data)[0]
    crop_name = encoder.inverse_transform([prediction])[0]
    return {"recommended_crop": crop_name}

# Run FastAPI with Uvicorn
if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000, reload=True)
print("hello world")