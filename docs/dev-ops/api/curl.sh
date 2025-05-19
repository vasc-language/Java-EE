curl http://117.72.211.70:11434/api/generate \
  -H "Content-Type: application/json" \
  -d '{
        "model": "deepseek-r1:1.5b",
        "prompt": "hi~",
        "stream": false
      }'