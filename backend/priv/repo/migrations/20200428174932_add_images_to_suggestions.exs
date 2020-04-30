defmodule IGovern.Repo.Migrations.AddImagesToSuggestions do
  use Ecto.Migration

  def change do
    alter table(:suggestions) do
      add :images, :map
    end
  end
end
