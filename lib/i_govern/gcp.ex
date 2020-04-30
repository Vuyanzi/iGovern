defmodule IGovern.GCP do
  use HTTPoison.Base

  def base_url do
    "https://storage.googleapis.com/upload/storage/v1/b/" <>
      Application.get_env(:i_govern, :bucket)
  end

  def process_url(url) do
    base_url() <> url
  end

  def process_request_body(body) do
    body
    |> Jason.encode!()
  end

  def process_response_body(body) do
    body
    |> Jason.decode!()
  end
end
