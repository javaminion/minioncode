gcloud functions deploy secedgardata \
    --runtime python310 \
    --trigger-http \
    --allow-unauthenticated \
    --entry-point secedgardata
