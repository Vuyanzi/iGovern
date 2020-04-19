defmodule IGovernWeb.SuggestionController do
  use IGovernWeb, :controller

  alias IGovern.Suggestions
  alias IGovern.Suggestions.Suggestion

  action_fallback IGovernWeb.FallbackController

  def index(conn, params) do
    suggestions = Suggestions.list_suggestions(params)
    render(conn, "index.json", suggestions: suggestions)
  end

  def create(conn, %{"suggestion" => suggestion_params}) do
    with {:ok, %Suggestion{} = suggestion} <- Suggestions.create_suggestion(suggestion_params) do
      conn
      |> put_status(:created)
      |> put_resp_header("location", Routes.suggestion_path(conn, :show, suggestion))
      |> render("show.json", suggestion: suggestion)
    end
  end

  def show(conn, %{"id" => id}) do
    suggestion = Suggestions.get_suggestion!(id)
    render(conn, "show.json", suggestion: suggestion)
  end

  def update(conn, %{"id" => id, "suggestion" => suggestion_params}) do
    suggestion = Suggestions.get_suggestion!(id)

    with {:ok, %Suggestion{} = suggestion} <-
           Suggestions.update_suggestion(suggestion, suggestion_params) do
      render(conn, "show.json", suggestion: suggestion)
    end
  end

  def delete(conn, %{"id" => id}) do
    suggestion = Suggestions.get_suggestion!(id)

    with {:ok, %Suggestion{}} <- Suggestions.delete_suggestion(suggestion) do
      send_resp(conn, :no_content, "")
    end
  end
end
