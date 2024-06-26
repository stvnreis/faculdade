paciente(bruna, 5).
paciente(felipe, 10).
paciente(luan, 50).

medico(artur).
medico(diana).

atendimento(artur, bruna).
atendimento(artur, felipe).
atendimento(diana, luan).

pediatra(NomeMedico) :- atendimento(NomeMedico, NomePaciente), paciente(NomePaciente, Idade),
    Idade < 12.