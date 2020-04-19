defmodule IGovern.Repo.Migrations.CreateSuggestions do
  use Ecto.Migration

  def change do
    create table(:suggestions) do
      add :device, :string
      add :suggestion, :string
      add :status, :string

      timestamps()
    end
  end
end
