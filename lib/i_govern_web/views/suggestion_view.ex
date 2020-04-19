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
      suggestion: suggestion.suggestion,
      status: suggestion.status,
      county: suggestion.county
    }
  end
end
