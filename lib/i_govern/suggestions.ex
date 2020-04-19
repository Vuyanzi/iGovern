defmodule IGovern.Suggestions do
  @moduledoc """
  The Suggestions context.
  """

  import Ecto.Query, warn: false
  alias IGovern.Repo

  alias IGovern.Suggestions.Suggestion

  @doc """
  Returns the list of suggestions as specified by the params.

  ## Examples

      iex> list_suggestions(params)
      [%Suggestion{}, ...]

  """
  def list_suggestions(params) do
    params
    |> Suggestion.filter()
    |> Repo.all()
  end

  @doc """
  Gets a single suggestion.

  Raises `Ecto.NoResultsError` if the Suggestion does not exist.

  ## Examples

      iex> get_suggestion!(123)
      %Suggestion{}

      iex> get_suggestion!(456)
      ** (Ecto.NoResultsError)

  """
  def get_suggestion!(id), do: Repo.get!(Suggestion, id)

  @doc """
  Creates a suggestion.

  ## Examples

      iex> create_suggestion(%{field: value})
      {:ok, %Suggestion{}}

      iex> create_suggestion(%{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def create_suggestion(attrs \\ %{}) do
    %Suggestion{}
    |> Suggestion.changeset(attrs)
    |> Repo.insert()
  end

  @doc """
  Updates a suggestion.

  ## Examples

      iex> update_suggestion(suggestion, %{field: new_value})
      {:ok, %Suggestion{}}

      iex> update_suggestion(suggestion, %{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def update_suggestion(%Suggestion{} = suggestion, attrs) do
    suggestion
    |> Suggestion.changeset(attrs)
    |> Repo.update()
  end

  @doc """
  Deletes a suggestion.

  ## Examples

      iex> delete_suggestion(suggestion)
      {:ok, %Suggestion{}}

      iex> delete_suggestion(suggestion)
      {:error, %Ecto.Changeset{}}

  """
  def delete_suggestion(%Suggestion{} = suggestion) do
    Repo.delete(suggestion)
  end

  @doc """
  Returns an `%Ecto.Changeset{}` for tracking suggestion changes.

  ## Examples

      iex> change_suggestion(suggestion)
      %Ecto.Changeset{source: %Suggestion{}}

  """
  def change_suggestion(%Suggestion{} = suggestion) do
    Suggestion.changeset(suggestion, %{})
  end
end
