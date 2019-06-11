       //#############################################
        // spinner relacionado a Contas

        spConta = findViewById(R.id.spinnerContas);

        ArrayAdapter<String> adapterSpContas =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_dropdown_item_1line,MESES);
        spConta.setAdapter(adapterSpContas);
        spConta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (MESES[position]){
                    case "Janeiro":
                        try {
                            spmesConta.setText(String.valueOf(balanco.
                                    getSumContas(ordenaMes.ordeC(SharedResources.getInstance().getContas(), 0)
                                            )));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Fevereiro":
                        try {
                            spmesConta.setText(String.valueOf(balanco.
                                    getSumContas(ordenaMes.ordeC(SharedResources.getInstance().getContas(), 1)
                                    )));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //#############################################
        // spinner relacionado a Receitas
        
        spiReceita = findViewById(R.id.spinerReceitas);
        ArrayAdapter<String> adapterSpReceitas =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_dropdown_item_1line,MESESR);
        spiReceita.setAdapter(adapterSpReceitas);
        spiReceita.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (MESESR[position]) {
                    case "Janeiro":

                        spmesesReceitas.setText(String.valueOf(balanco.
                                getSumReceitas(ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas(), 0)
                                )));
                    case "Fevereiro":
                        spmesesReceitas.setText(String.valueOf(balanco.
                                getSumReceitas(ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas(), 1)
                                )));


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });









    public void decrementMain(View view) throws ParseException {
        super.onResume();

        cont--;

        if(cont > 11 || cont < 0){ cont =0; }
        filtrmeses.setText(meses[cont]);


            resumo.setText(String.valueOf(balanco.resumo(
                    ordenaMes.ordeC(SharedResources.getInstance().getContas(),
                            cont),

                    ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas()
                            ,cont))));

        spmesesReceitas.setText(String.valueOf(balanco.resumo(
                ordenaMes.ordeC(SharedResources.getInstance().getContas(),
                        cont),

                ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas()
                        ,cont))));

        spmesesReceitas.setText(String.valueOf(balanco.getSumReceitas(
                ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas(),
                        cont))));

        spmesConta.setText(String.valueOf(balanco.getSumContas(
                ordenaMes.ordeC(SharedResources.getInstance().getContas(),
                        cont))));



    }

    public void incrementMain(View view) throws ParseException {
        super.onResume();

        cont++;

        if(cont > 11 || cont < 0){ cont =0; }
        filtrmeses.setText(meses[cont]);

            resumo.setText(String.valueOf(balanco.resumo(
                    ordenaMes.ordeC(SharedResources.getInstance().getContas(),
                            cont),

                    ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas()
                            ,cont))));


        spmesesReceitas.setText(String.valueOf(balanco.resumo(
                ordenaMes.ordeC(SharedResources.getInstance().getContas(),
                        cont),

                ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas()
                        ,cont))));

        spmesesReceitas.setText(String.valueOf(balanco.getSumReceitas(
                ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas(),
                        cont))));

        spmesConta.setText(String.valueOf(balanco.getSumContas(
                ordenaMes.ordeC(SharedResources.getInstance().getContas(),
                        cont))));

    }






   configurations.all {
        resolutionStrategy.eachDependency { DependencyResolveDetails details ->
            def requested = details.requested
            if (requested.group == 'com.android.support') {
                if (!requested.name.startsWith("multidex")) {
                    details.useVersion '26.0.0'
                }
            }
        }
    }
