defmodule IGovernWeb.SuggestionView do
  use IGovernWeb, :view
  alias IGovernWeb.SuggestionView

  def render("index.json", %{suggestions: suggestions}) do
    %{data: render_many(suggestions, SuggestionView, "suggestion.json")}
  end

  def render("show.json", %{suggestion: suggestion}) do
    %{data: render_one(suggestion, SuggestionView, "suggestion.json")}
  end

  def render("suggestion.json", %{suggestion: suggestion}) do
    %{
      id: suggestion.id,
      device: suggestion.device,
      content: suggestion.content,
      status: suggestion.status,
      county: suggestion.county,
      title: suggestion.title,
      type: suggestion.type,
      inserted_at: NaiveDateTime.add(suggestion.inserted_at, 3600 * 3)
    }
  end
end
